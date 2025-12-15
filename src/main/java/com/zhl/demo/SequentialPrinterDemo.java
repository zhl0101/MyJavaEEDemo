package com.zhl.demo;

/**
 * 演示SequentialPrinter类的使用
 */
public class SequentialPrinterDemo {
    public static void main(String[] args) {
        // 创建SequentialPrinter实例，设置最大值为10
        SequentialPrinter printer = new SequentialPrinter(10);
        
        System.out.println("=== 顺序打印奇数 ===");
        printer.printOdd();
        
        System.out.println("\n=== 顺序打印偶数 ===");
        printer.printEven();
        
        System.out.println("\n=== 交替打印奇数和偶数 ===");
        // 交替打印奇数和偶数
        for (int i = 1; i <= printer.getMaxcount(); i++) {
            if (i % 2 == 1) {
                System.out.println("奇数: " + i);
            } else {
                System.out.println("偶数: " + i);
            }
        }
    }
}