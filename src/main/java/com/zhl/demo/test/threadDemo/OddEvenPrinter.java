package com.zhl.demo.test.threadDemo;

/**
 * 交替打印奇数和偶数的实现
 * 一个线程打印奇数，另一个线程打印偶数，按顺序输出1,2,3,4,5...
 */
public class OddEvenPrinter {
    private static final Object lock = new Object();
    private static int count = 1;
    private static final int MAX_COUNT = 20; // 设置最大打印到20

    public static void main(String[] args) {
        // 创建打印奇数的线程
        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count <= MAX_COUNT) {
                    synchronized (lock) {
                        // 如果是奇数，则打印
                        if (count % 2 == 1) {
                            System.out.println("奇数线程: " + count);
                            count++;
                            lock.notifyAll();
                        } else {
                            try {
                                // 如果不是奇数，则等待
                                lock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
        });

        // 创建打印偶数的线程
        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count <= MAX_COUNT) {
                    synchronized (lock) {
                        // 如果是偶数，则打印
                        if (count % 2 == 0) {
                            System.out.println("偶数线程: " + count);
                            count++;
                            lock.notifyAll();
                        } else {
                            try {
                                // 如果不是偶数，则等待
                                lock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
        });

        // 启动线程
        oddThread.start();
        evenThread.start();
    }
}