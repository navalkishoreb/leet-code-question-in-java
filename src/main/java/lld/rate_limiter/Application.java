package lld.rate_limiter;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Application {
    public static void main(String[] args) {
        int clientCount = 5;
        ExecutorService clientPool = Executors.newFixedThreadPool(clientCount);
        ExecutorService consumerPool = Executors.newFixedThreadPool(1);
        ConsumerService consumerService = new ConsumerService(consumerPool);
        RateLimiter rateLimiter = new LeakyBucketRateLimiter(5, 10);
        ApiServive apiServive = new ApiServive(consumerService, rateLimiter);

        for (int i = 0; i < clientCount; i++) {
            clientPool.submit(new Client("p" + i, apiServive, 500));
        }
        logAtEverySecond();
    }

    private static void logAtEverySecond() {
        while (true) {
            System.out.println("--------------" + LocalDateTime.now() + "---------------");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Client implements Runnable {

    private final String id;
    private final ApiServive apiServive;
    private final int sleep;

    public Client(String id, ApiServive apiServive, int sleep) {
        this.id = id;
        this.apiServive = apiServive;
        this.sleep = sleep;
    }

    @Override
    public void run() {
        int i = 1;
        while (true) {
            Message message = new Message(id, i++);
            if (!apiServive.post(message)) {
                System.out.println("[Client] rejected " + Thread.currentThread().getName() + " " + message);
            }
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

}

class Task implements Runnable {
    private final Message message;

    Task(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println("[Consumer] " + Thread.currentThread().getName() + " " + message);
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ConsumerService {
    private final ExecutorService service;

    ConsumerService(ExecutorService service) {
        this.service = service;
    }

    void submit(Message message) {
        service.submit(new Task(message));
    }
}

interface RateLimiter {
    boolean allow();
}

class LeakyBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private long lastLeakTime;
    private final ReentrantLock lock;
    private final double leakRatePerMillis;
    private double currentWaterLevel;

    LeakyBucketRateLimiter(int tps, int capacity) {
        this.leakRatePerMillis = tps / 1000.0;
        this.capacity = capacity;
        this.lastLeakTime = System.currentTimeMillis();
        this.currentWaterLevel = capacity;
        this.lock = new ReentrantLock(true);
    }

    @Override
    public boolean allow() {
        lock.lock();
        try {
            leak();
            if (currentWaterLevel < capacity) {
                currentWaterLevel += 1;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void leak() {
        long now = System.currentTimeMillis();
        long elapsedInMillis = now - lastLeakTime;
        if (elapsedInMillis <= 0) {
            return;
        }
        double tokensToRelease = (elapsedInMillis * leakRatePerMillis);
        currentWaterLevel = Math.max(0, currentWaterLevel - tokensToRelease);
        lastLeakTime = now;
    }
}


class ApiServive {
    private final ConsumerService consumerService;
    private final RateLimiter rateLimiter;

    ApiServive(ConsumerService consumerService, RateLimiter rateLimiter) {
        this.consumerService = consumerService;
        this.rateLimiter = rateLimiter;
    }

    public boolean post(Message message) {
        if (rateLimiter.allow()) {
            consumerService.submit(message);
            return true;
        }
//        System.out.print("Message " + message.message() + " sent by " + message.producer() + "dicarded by ratelimiter");
        return false;
    }
}

record Message(String producer, int message) {
    @Override
    public String toString() {
        return producer + "_" + message;
    }
}


