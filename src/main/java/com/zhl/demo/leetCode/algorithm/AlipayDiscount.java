package com.zhl.demo.leetCode.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AlipayDiscount {
    
    /**
     * 计算使用支付宝最多能买多少件物品
     * @param n 物品总数
     * @param k 支付宝余额
     * @param prices 物品价格数组
     * @param discountFlags 折扣标记数组（1表示支持折扣，0表示不支持）
     * @return 最多能买的物品数量
     */
    public static int maxItems(int n, int k, int[] prices, String discountFlags) {
        List<Integer> discountItems = new ArrayList<Integer>();  // 支持优惠的物品
        List<Integer> normalItems = new ArrayList<Integer>();    // 不支持优惠的物品
        
        // 分类物品
        for (int i = 0; i < n; i++) {
            if (discountFlags.charAt(i) == '1') {
                // 计算优惠价格（九五折）
                int discountedPrice = (int) Math.floor(prices[i] * 0.95);
                discountItems.add(discountedPrice);
            } else {
                normalItems.add(prices[i]);
            }
        }
        
        // 排序，便宜的放前面
        Collections.sort(discountItems);
        Collections.sort(normalItems);
        
        int count = 0;
        int balance = k;
        
        // 贪心策略：每次选择能买的最便宜物品
        int discountIndex = 0;
        int normalIndex = 0;
        
        while (balance > 0) {
            int discountPrice = Integer.MAX_VALUE;
            int normalPrice = Integer.MAX_VALUE;
            
            // 获取下一个可购买的优惠物品价格
            if (discountIndex < discountItems.size()) {
                discountPrice = discountItems.get(discountIndex);
            }
            
            // 获取下一个可购买的普通物品价格
            if (normalIndex < normalItems.size()) {
                normalPrice = normalItems.get(normalIndex);
            }
            
            // 如果两种物品都无法购买，则退出
            if (discountPrice > balance && normalPrice > balance) {
                break;
            }
            
            // 选择更便宜且负担得起的物品购买
            if (discountPrice <= normalPrice && discountPrice <= balance) {
                balance -= discountPrice;
                discountIndex++;
                count++;
            } else if (normalPrice <= balance) {
                balance -= normalPrice;
                normalIndex++;
                count++;
            } else {
                break;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        
        String discountFlags = scanner.next();
        
        int result = maxItems(n, k, prices, discountFlags);
        System.out.println(result);
        
        scanner.close();
    }
}