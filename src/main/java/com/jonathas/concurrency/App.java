package com.jonathas.concurrency;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        Counter counter = new Counter();
        CounterRunnable c1 = new CounterRunnable(counter, "T1");
        CounterRunnable c2 = new CounterRunnable(counter, "T2");
        Thread thread1 = new Thread(c1);
        Thread thread2 = new Thread(c2);
        thread1.start();
        thread2.start();
        Thread.sleep(4000);
        System.out.println( "Hello World! The value was " + counter.count );
    }

}
 class CounterRunnable implements Runnable {
    private Counter counter;
    private String name;
    public CounterRunnable(Counter counter, String name) { this.counter = counter; this.name = name; }
    public void run() {
        int i = 0;
        while(i++ < 10) {
            increment();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized public void increment() {
        System.out.println(String.format("Thread %s call count %d", this.name, counter.count));
        counter.count++;
    }
}

class Counter {
    public int count;
}
