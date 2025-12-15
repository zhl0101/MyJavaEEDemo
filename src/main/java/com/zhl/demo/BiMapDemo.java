package com.zhl.demo;

/**
 * BiMap的演示类
 */
public class BiMapDemo {
    public static void main(String[] args) {
        // 创建一个BiMap实例
        BiMap<String, Integer> biMap = new BiMap<>();
        
        // 添加一些键值对
        biMap.put("一", 1);
        biMap.put("二", 2);
        biMap.put("三", 3);
        
        // 展示通过key获取value
        System.out.println("通过key \"一\" 获取value: " + biMap.getValue("一"));
        System.out.println("通过key \"二\" 获取value: " + biMap.getValue("二"));
        
        // 展示通过value获取key
        System.out.println("通过value 1 获取key: " + biMap.getKey(1));
        System.out.println("通过value 2 获取key: " + biMap.getKey(2));
        
        // 展示其他功能
        System.out.println("BiMap大小: " + biMap.size());
        System.out.println("是否包含key \"三\": " + biMap.containsKey("三"));
        System.out.println("是否包含value 3: " + biMap.containsValue(3));
        
        // 删除操作
        biMap.removeByKey("二");
        System.out.println("删除key \"二\" 后，BiMap内容: " + biMap);
        System.out.println("此时BiMap大小: " + biMap.size());
    }
}