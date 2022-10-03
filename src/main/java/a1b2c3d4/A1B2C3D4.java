package a1b2c3d4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class A1B2C3D4 {
    static final char[] c1 = "ABCDEFG".toCharArray();
    static final char[] c2 = "1234567".toCharArray();

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock();

        Synchronized();
    }

    public static void ReentrantLock() throws InterruptedException {

        Lock l = new ReentrantLock();
        Condition cd1 = l.newCondition();
        Condition cd2 = l.newCondition();

        Thread t1 = new Thread(() -> {
            try {
                l.lock();
                for (char c : c1) {
                    System.out.print(c);
                    cd2.signal();
                    cd1.await();
                }
                cd2.signal();
            } catch (Exception e) {

            } finally {
                l.unlock();
            }

        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                l.lock();
                for (char c : c2) {
                    System.out.print(c);
                    cd1.signal();
                    cd2.await();
                }
                cd1.signal();
            } catch (Exception e) {

            } finally {
                l.unlock();
            }
        }, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println("");
    }


    public static void Synchronized() throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (char c : c1) {
                    System.out.print(c);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lock.notifyAll();
            }
        }, "t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (char c : c2) {
                    System.out.print(c);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lock.notifyAll();
            }
        }, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println("");
    }

}
