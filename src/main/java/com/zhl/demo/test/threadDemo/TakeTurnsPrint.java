package com.zhl.demo.test.threadDemo;

/**
 *
 *   两个线程轮流打印数字，一直到100
 *   Java的wait()、notify()学习：
 *
 */
public class TakeTurnsPrint {
    public static void main(String[] args) {
        TakeTurns takeTurns = new TakeTurns();

        new Thread(new Runnable() {
            @Override
            public void run() {
                takeTurns.print1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                takeTurns.print2();
            }
        }).start();

    }

    public static class TakeTurns {
        private static boolean flag = true;

        private static int count = 0;

        public synchronized void print1() {
            for (int i = 1; i <= 50; i++) {
                while (!flag) {
                    try {
                        System.out.println("print1: wait before");
                        wait();
                        System.out.println("print1: wait after");
                    } catch (InterruptedException e) {
                    }
                }

                System.out.println("print1: " + ++count);
                flag = !flag;
                notifyAll();
            }
        }

        public synchronized void print2() {
            for (int i = 1; i <= 50; i++) {
                while (flag) {
                    try {
                        System.out.println("print2: wait before");
                        wait();
                        System.out.println("print2: wait after");
                    } catch (InterruptedException e) {
                    }
                }

                System.out.println("print2: " + ++count);
                flag = !flag;
                notifyAll();
            }
        }
    }


}
