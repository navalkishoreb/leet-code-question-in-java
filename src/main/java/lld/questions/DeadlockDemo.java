package lld.questions;

public class DeadlockDemo {
    private static final Object lock1 =  new Object();
    private static final Object lock2 =  new Object();

    public static void main(String[] args){
        Thread thread1 = process1();
        Thread thread2 = process2();
        thread1.start();
        thread2.start();

    }

    private static Thread process1(){
        Thread thread = new Thread(new Runnable() {
          public void run(){
            System.out.println("thread1 started");
            System.out.println("thread1 waiting for lock 1");
            synchronized(lock1){
                System.out.println("thread1 acquired lock 1");
                sleep();
                System.out.println("thread1 waiting for lock 2");
                synchronized(lock2){
                    System.out.println("thread1 acquired lock 2");
                    sleep();
                }
            }
          }
        });
        return thread;

    }

    private static Thread process2(){
        Thread thread = new Thread(new Runnable() {
          public void run(){
            System.out.println("thread2 started");
            System.out.println("thread2 waiting for lock 2");
            synchronized(lock2){
                System.out.println("thread2 acquired lock 2");
                sleep();
                System.out.println("thread2 waiting for lock 1");
                synchronized(lock1){
                    System.out.println("thread2 acquired lock 1");
                    sleep();
                }
            }
          }
        });
        return thread;
    }

    private static void sleep(){

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
