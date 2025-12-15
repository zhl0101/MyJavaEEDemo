package com.zhl.demo;

/**
 * 顺序打印奇数和偶数的类
 * 包含一个maxcount属性，以及打印奇数和偶数的方法
 */
public class SequentialPrinter {
    private int maxcount;

    /**
     * 构造函数
     * @param maxcount 最大计数值
     */
    public SequentialPrinter(int maxcount) {
        this.maxcount = maxcount;
    }

    /**
     * 打印奇数
     */
    public void printOdd() {
        for (int i = 1; i <= maxcount; i += 2) {
            System.out.println("奇数: " + i);
        }
    }

    /**
     * 打印偶数
     */
    public void printEven() {
        for (int i = 2; i <= maxcount; i += 2) {
            System.out.println("偶数: " + i);
        }
    }

    /**
     * 获取最大计数值
     * @return maxcount
     */
    public int getMaxcount() {
        return maxcount;
    }

    /**
     * 设置最大计数值
     * @param maxcount 最大计数值
     */
    public void setMaxcount(int maxcount) {
        this.maxcount = maxcount;
    }
}