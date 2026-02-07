package lld.elastic_search;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) throws InterruptedException {
//        testSearch();
//        testMultiThread(1000);
        testSearchOOM();
    }


    private static void testSearch() {
        Search search = new Search();
        search.add("mango is a fruit");
        search.add("guava is a fruit");
        search.add("not all fruits are vegetables all vegetables are fruit");
        System.out.println(search.find("fruit"));
    }


    private static void testMultiThread(int iterations) throws InterruptedException {
        Search search = new Search();
        int threads = 10;
        CountDownLatch latch = new CountDownLatch(threads);

        ExecutorService pool = Executors.newFixedThreadPool(threads);
        for (int t = 0; t < threads; t++) {
            int threadId = t;
            pool.submit(() -> {
                for (int i = 0; i < iterations; i++) {
                    search.add("this is some java sentence written by thread " + threadId);
                }
                latch.countDown();
            });
        }

        latch.await();
        pool.shutdown();
        System.out.println("Results for 'java':");
        System.out.println(search.find("java").size());
    }

    private static void testSearchOOM() throws InterruptedException {
        // running with vm options
        // -Xms16m -Xmx32m -Xlog:gc
        // Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "pool-1-thread-6"
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        // Search() can have max 45K entries
        // Search2() can have 53K entries not beyond that
        Cache search = new Search3();
        int threads = 10;
        int iterations = 10_000;
        CountDownLatch latch = new CountDownLatch(threads);

        ExecutorService pool = Executors.newFixedThreadPool(threads);
        for (int t = 0; t < threads; t++) {
            int threadId = t;
            pool.submit(() -> {
                try {
                    for (int i = 0; i < iterations; i++) {
                        if (i % 1000 == 0) {
                            System.out.println(LocalDateTime.now() + " thread: " + threadId + " at iteration " + i);
                        }
                        search.add("this is some java sentence written by thread " + threadId + " iteration " + i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }

            });
        }

        latch.await();
        pool.shutdown();
        System.out.println("Results for 'java':");
        System.out.println(search.find("java").size());
    }


}
