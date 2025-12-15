package com.zhl.demo.test.threadDemo;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    public CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    private class Student implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "到达教室");
                cyclicBarrier.await(); // 等待所有学生到达
                System.out.println(Thread.currentThread().getName() + "等待上课");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Student(), "学生" + i).start();
        }
    }

    public static void main(String[] args) {
        new TestCyclicBarrier().start();
    }


}
