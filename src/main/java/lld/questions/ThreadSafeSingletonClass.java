package lld.questions;

public class ThreadSafeSingletonClass {
    // double check required volatile as second thread can see the 
    // variable updates immediately
    private static volatile ThreadSafeSingletonClass instance;

    // make constructor private
    private ThreadSafeSingletonClass(){}

    // we are avoiding synchronized method for performance
    // as after creation we don't want locking mechanism
    public static ThreadSafeSingletonClass getInstance(){
        if(instance == null){
            synchronized(ThreadSafeSingletonClass.class){
                /*
                Scenario if we REMOVE the second check:
                Thread A passes first check
                Thread A acquires lock, creates instance
                Thread B was waiting on lock
                Thread B enters synchronized block
                Thread B creates another instance ❌
                */
                if(instance == null){
                    instance = new ThreadSafeSingletonClass();
                }
            }
        }
        return instance;
    }

}

/*
How Holder Pattern is thread safe
JVM class initialization guarantee 🔒

        The Java Language Specification guarantees:
        Class initialization is synchronized
        Only one thread can initialize a class
        Other threads block until initialization completes
        Initialization happens exactly once

So if:
    Thread A reaches Holder.INSTANCE
    Thread B reaches Holder.INSTANCE at the same time
Then:
    Thread A initializes Holder
    Thread B waits
    Both see the same fully constructed INSTANCE

    
 */
class Singleton {
    private Singleton() {}

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}