package com.zhl.demo.test;

import java.util.Random;
import java.util.Scanner;

/**
 * 简单的猜数字游戏
 * 游戏规则：计算机随机生成一个1-100之间的数字，玩家需要猜测这个数字是多少
 * 玩家每次猜测后，计算机会提示"太大"、"太小"或"正确"
 */
public class SimpleGame {
    
    private Random random;
    private Scanner scanner;
    private int targetNumber;
    private int maxAttempts;
    private int attempts;
    
    /**
     * 构造函数，初始化游戏
     */
    public SimpleGame() {
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        this.maxAttempts = 10; // 最大尝试次数
        this.attempts = 0;
    }
    
    /**
     * 开始游戏
     */
    public void startGame() {
        System.out.println("欢迎来到猜数字游戏！");
        System.out.println("我想了一个1到100之间的数字，你有" + maxAttempts + "次机会猜中它。");
        System.out.println("祝你好运！\n");
        
        // 生成目标数字（1-100之间）
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        
        // 游戏主循环
        boolean gameWon = false;
        while (attempts < maxAttempts && !gameWon) {
            int remainingAttempts = maxAttempts - attempts;
            System.out.println("你还有" + remainingAttempts + "次机会。");
            System.out.print("请输入你猜测的数字（1-100）：");
            
            try {
                int guess = scanner.nextInt();
                attempts++;
                
                if (guess < 1 || guess > 100) {
                    System.out.println("请输入1到100之间的数字！\n");
                    attempts--; // 不合法的输入不计入尝试次数
                    continue;
                }
                
                if (guess == targetNumber) {
                    System.out.println("恭喜你！你猜对了！");
                    System.out.println("答案就是 " + targetNumber);
                    System.out.println("你总共用了 " + attempts + " 次尝试。\n");
                    gameWon = true;
                } else if (guess < targetNumber) {
                    System.out.println("太小了！再试一次。\n");
                } else {
                    System.out.println("太大了！再试一次。\n");
                }
            } catch (Exception e) {
                System.out.println("请输入一个有效的数字！\n");
                scanner.nextLine(); // 清除无效输入
                attempts--; // 不合法的输入不计入尝试次数
            }
        }
        
        // 游戏结束
        if (!gameWon) {
            System.out.println("很遗憾，你没有在规定次数内猜中数字。");
            System.out.println("正确答案是: " + targetNumber);
        }
        
        // 询问是否再玩一次
        playAgain();
    }
    
    /**
     * 询问玩家是否再玩一次
     */
    private void playAgain() {
        System.out.print("你想再玩一次吗？(y/n): ");
        String choice = scanner.next();
        
        if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
            System.out.println("\n" + "========================================" + "\n");
            startGame(); // 重新开始游戏
        } else {
            System.out.println("谢谢游戏！再见！");
            scanner.close();
        }
    }
    
    /**
     * 获取目标数字（仅用于测试目的）
     * @return 目标数字
     */
    public int getTargetNumber() {
        return targetNumber;
    }
    
    /**
     * 获取已尝试次数
     * @return 已尝试次数
     */
    public int getAttempts() {
        return attempts;
    }
    
    /**
     * 主方法，程序入口
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SimpleGame game = new SimpleGame();
        game.startGame();
    }
}