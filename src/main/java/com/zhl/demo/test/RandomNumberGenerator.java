package com.zhl.demo.test;

import java.util.*;

public class RandomNumberGenerator {
    
    /**
     * 生成符合双色球规则的号码列表
     * 前6位为不重复的1-33数字，第7位为1-16数字
     * @return 包含7个元素的列表
     */
    public static List<Integer> generateSSQNumbers() {
        List<Integer> result = new ArrayList<>(7);
        Set<Integer> usedNumbers = new HashSet<>();
        Random random = new Random();
        
        // 生成前6个不重复的红球号码(1-33)
        while (result.size() < 6) {
            int number = random.nextInt(33) + 1;
            if (!usedNumbers.contains(number)) {
                usedNumbers.add(number);
                result.add(number);
            }
        }
        
        // 生成蓝球号码(1-16)
        int blueNumber = random.nextInt(16) + 1;
        result.add(blueNumber);
        
        return result;
    }
    
    /**
     * 生成并排序前6位数字的版本
     * @return 排序后的符合规则的列表
     */
    public static List<Integer> generateSortedSSQNumbers() {
        List<Integer> numbers = generateSSQNumbers();
        // 对前6个红球号码进行排序
        List<Integer> redBalls = numbers.subList(0, 6);
        Collections.sort(redBalls);
        return numbers;
    }

    public static void main(String[] args) {
        List<Integer> numbers = generateSSQNumbers();
        System.out.println("生成的号码：" + numbers);

        List<Integer> sortedNumbers = generateSortedSSQNumbers();
        System.out.println("排序后的号码：" + sortedNumbers);
    }
}
