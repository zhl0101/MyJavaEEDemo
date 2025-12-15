package com.zhl.demo.test.threadDemo;

import com.zhl.demo.SequentialPrinter;

/**
 * 在多线程环境中交替打印奇数和偶数
 */
public class ConcurrentSequentialPrinter {
    private static final Object lock = new Object();
    private static int currentNumber = 1;
    private SequentialPrinter printer;
    
    public ConcurrentSequentialPrinter(SequentialPrinter printer) {
        this.printer = printer;
    }

    /**
     * 打印奇数的方法
     */
    public void printOdd() {
        synchronized (lock) {
            while (currentNumber <= printer.getMaxcount()) {
                if (currentNumber % 2 == 1) {
                    System.out.println("奇数: " + currentNumber);
                    currentNumber++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            // 确保所有等待的线程都能退出
            lock.notifyAll();
        }
    }

    /**
     * 打印偶数的方法
     */
    public void printEven() {
        synchronized (lock) {
            while (currentNumber <= printer.getMaxcount()) {
                if (currentNumber % 2 == 0) {
                    System.out.println("偶数: " + currentNumber);
                    currentNumber++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            // 确保所有等待的线程都能退出
            lock.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SequentialPrinter printer = new SequentialPrinter(10);
        ConcurrentSequentialPrinter concurrentPrinter = new ConcurrentSequentialPrinter(printer);

        Thread oddThread = new Thread(concurrentPrinter::printOdd);
        Thread evenThread = new Thread(concurrentPrinter::printEven);

        System.out.println("=== 并发交替打印奇数和偶数 ===");
        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();
        System.out.println("=== 打印完成 ===");
    }
}