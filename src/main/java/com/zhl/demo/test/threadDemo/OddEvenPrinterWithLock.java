package com.zhl.demo.test.threadDemo;

/**
 * 使用ReentrantLock和Condition实现交替打印奇数和偶数
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterWithLock {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition oddCondition = lock.newCondition();
    private static Condition evenCondition = lock.newCondition();
    private static int count = 1;
    private static final int MAX_COUNT = 20;

    public static void main(String[] args) {
        // 打印奇数的线程
        Thread oddThread = new Thread(() -> {
            try {
                lock.lock();
                while (count <= MAX_COUNT) {
                    if (count % 2 == 1) {
                        System.out.println("奇数线程: " + count);
                        count++;
                        evenCondition.signal();
                    } else {
                        // 等待偶数线程打印完唤醒
                        oddCondition.await();
                    }
                }
                // 通知偶数线程结束
                evenCondition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        // 打印偶数的线程
        Thread evenThread = new Thread(() -> {
            try {
                lock.lock();
                while (count <= MAX_COUNT) {
                    if (count % 2 == 0) {
                        System.out.println("偶数线程: " + count);
                        count++;
                        oddCondition.signal();
                    } else {
                        // 等待奇数线程打印完唤醒
                        evenCondition.await();
                    }
                }
                // 通知奇数线程结束
                oddCondition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        oddThread.start();
        evenThread.start();
    }
}