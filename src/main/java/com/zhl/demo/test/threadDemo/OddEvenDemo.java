package com.zhl.demo.test.threadDemo;

/**
 * 按顺序打印奇数偶数的演示类
 */
public class OddEvenDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 开始交替打印奇数和偶数 ===");
        
        // 使用Runnable的方式实现
        OddEvenRunnable runnable1 = new OddEvenRunnable(1); // 打印奇数
        OddEvenRunnable runnable2 = new OddEvenRunnable(2); // 打印偶数
        
        Thread t1 = new Thread(runnable1, "奇数线程");
        Thread t2 = new Thread(runnable2, "偶数线程");
        
        t1.start();
        t2.start();
        
        // 等待两个线程执行完毕
        t1.join();
        t2.join();
        
        System.out.println("=== 执行完毕 ===");
    }
    
    static class OddEvenRunnable implements Runnable {
        private static final Object lock = new Object();
        private static int number = 1;
        private static final int MAX = 20;
        
        // type: 1表示打印奇数, 2表示打印偶数
        private int type;
        
        public OddEvenRunnable(int type) {
            this.type = type;
        }
        
        @Override
        public void run() {
            while (number <= MAX) {
                synchronized (lock) {
                    // 判断当前数字是否符合当前线程的打印条件
                    if (number % 2 == (type - 1)) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        lock.notifyAll(); // 唤醒其他等待的线程
                        
                        // 如果已经达到最大值，退出循环
                        if (number > MAX) {
                            break;
                        }
                    } else {
                        try {
                            // 不符合条件则等待
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
            
            // 确保最后能唤醒其他可能还在等待的线程
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }
}