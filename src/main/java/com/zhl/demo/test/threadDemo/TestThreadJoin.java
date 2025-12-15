package com.zhl.demo.test.threadDemo;

public class TestThreadJoin {

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadC threadC = new ThreadC();
        try {
            threadA.start();
            threadA.join();

            threadB.start();
            threadB.join();

            threadC.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static class ThreadA extends Thread {
        @Override
        public void run() {
            System.out.println("ThreadA start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA end");
        }
    }

    public static class ThreadB extends Thread {
        @Override
        public void run() {
            System.out.println("ThreadB start");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadB end");
        }
    }

    public static class ThreadC extends Thread {
        @Override
        public void run() {
            System.out.println("ThreadC start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadC end");
        }
    }
}
