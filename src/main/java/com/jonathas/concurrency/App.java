package com.jonathas.concurrency;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(new CounterRunnable(counter, "T1"));
        Thread thread2 = new Thread(new CounterRunnable(counter, "T2"));
        Thread thread3 = new Thread(new CounterRunnable(counter, "T3"));

        thread1.start();
        thread2.start();
        thread3.start();
        while(thread1.isAlive() && thread2.isAlive()) {
            System.out.println("Main thread will be alive till the child thread is live");
            Thread.sleep(1000);
        }
        System.out.println( "Hello World! The value was " + counter.count );
    }

}
 class CounterRunnable implements Runnable {
    private Counter counter;
    private String name;
    public CounterRunnable(Counter counter, String name) { this.counter = counter; this.name = name; }
    public void run() {
        int i = 0;
        while(i++ < 1000000) {
            counter.increment();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class Counter {
    public int count;
    public synchronized void increment() {
        count++;
    }
}
