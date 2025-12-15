package com.zhl.demo;

/**
 * 改进版的顺序打印类，支持交替打印奇数和偶数
 */
public class AdvancedSequentialPrinter {
    private int maxcount;
    private static final Object lock = new Object();
    private static volatile int current = 1;

    /**
     * 构造函数
     *
     * @param maxcount 最大计数值
     */
    public AdvancedSequentialPrinter(int maxcount) {
        this.maxcount = maxcount;
    }

    /**
     * 打印奇数（线程安全）
     */
    public void printOdd() {
        synchronized (lock) {
            while (current <= maxcount) {
                if (current % 2 == 1) {
                    System.out.println("奇数: " + current);
                    current++;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            lock.notifyAll(); // 唤醒所有等待的线程以确保正常退出
        }
    }

    /**
     * 打印偶数（线程安全）
     */
    public void printEven() {
        synchronized (lock) {
            while (current <= maxcount) {
                if (current % 2 == 0) {
                    System.out.println("偶数: " + current);
                    current++;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            lock.notifyAll(); // 唤醒所有等待的线程以确保正常退出
        }
    }

    /**
     * 顺序打印所有数字（非并发方式）
     */
    public void printSequential() {
        for (int i = 1; i <= maxcount; i++) {
            if (i % 2 == 1) {
                System.out.println("奇数: " + i);
            } else {
                System.out.println("偶数: " + i);
            }
        }
    }

    /**
     * 获取最大计数值
     *
     * @return maxcount
     */
    public int getMaxcount() {
        return maxcount;
    }

    /**
     * 设置最大计数值
     *
     * @param maxcount 最大计数值
     */
    public void setMaxcount(int maxcount) {
        this.maxcount = maxcount;
    }

    /**
     * 重置计数器
     */
    public void reset() {
        synchronized (lock) {
            current = 1;
        }
    }
}