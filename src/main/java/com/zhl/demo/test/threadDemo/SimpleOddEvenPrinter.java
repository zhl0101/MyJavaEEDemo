package com.zhl.demo.test.threadDemo;

/**
 * 简化版本的交替打印奇数和偶数实现
 */
public class SimpleOddEvenPrinter {
    private static final Object lock = new Object();
    private static int number = 1;
    private static final int MAX = 20;

    public static void main(String[] args) {
        // 奇数打印线程
        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (number <= MAX) {
                    synchronized (lock) {
                        // 如果是奇数就打印
                        if (number % 2 == 1) {
                            System.out.println("奇数: " + number);
                            number++;
                            lock.notify();
                        } else {
                            // 是偶数就等待
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                    }
                }
            }
        });

        // 偶数打印线程
        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (number <= MAX) {
                    synchronized (lock) {
                        // 如果是偶数就打印
                        if (number % 2 == 0) {
                            System.out.println("偶数: " + number);
                            number++;
                            lock.notify();
                        } else {
                            // 是奇数就等待
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
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