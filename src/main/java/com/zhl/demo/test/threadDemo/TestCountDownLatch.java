package com.zhl.demo.test.threadDemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    private CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {
        TestCountDownLatch testCountDownLatch = new TestCountDownLatch();
        testCountDownLatch.start();
    }

    // 运动员类 模拟跑步比赛
    public class Athlete implements Runnable {
        private int time;

        public Athlete(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                // 模拟跑的时间
                Thread.sleep(time+1000);
                System.out.println(Thread.currentThread().getName() + "到达终点");
                countDownLatch.countDown(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void start() {
        System.out.println("开始比赛");
        // 设置随机时间
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            int time = random.nextInt(3) + 1;
            new Thread(new Athlete(time)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有运动员到达终点");
    }
}