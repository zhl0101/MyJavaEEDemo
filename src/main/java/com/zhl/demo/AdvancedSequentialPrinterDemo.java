package com.zhl.demo;

/**
 * AdvancedSequentialPrinter的演示类
 */
public class AdvancedSequentialPrinterDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 演示AdvancedSequentialPrinter ===");

        // 演示顺序打印
        AdvancedSequentialPrinter printer = new AdvancedSequentialPrinter(10);
        System.out.println("--- 顺序打印 ---");
        printer.printSequential();

        System.out.println("\n--- 并发交替打印 ---");
        // 演示并发交替打印
        printer.reset();

        Thread oddThread = new Thread(printer::printOdd, "奇数线程");
        Thread evenThread = new Thread(printer::printEven, "偶数线程");

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();

        System.out.println("=== 演示完成 ===");
    }
}