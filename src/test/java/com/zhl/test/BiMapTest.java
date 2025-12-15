package com.zhl.test;

import com.zhl.demo.BiMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BiMap类的单元测试
 */
public class BiMapTest {

    @Test
    public void testPutAndGet() {
        BiMap<String, Integer> biMap = new BiMap<>();
        
        // 测试添加键值对
        biMap.put("one", 1);
        biMap.put("two", 2);
        
        // 测试通过key获取value
        assertEquals(1, biMap.getValue("one"));
        assertEquals(2, biMap.getValue("two"));
        
        // 测试通过value获取key
        assertEquals("one", biMap.getKey(1));
        assertEquals("two", biMap.getKey(2));
    }

    @Test
    public void testDuplicateKey() {
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.put("one", 1);
        
        // 测试重复key抛出异常
        assertThrows(IllegalArgumentException.class, () -> {
            biMap.put("one", 2);
        });
    }

    @Test
    public void testDuplicateValue() {
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.put("one", 1);
        
        // 测试重复value抛出异常
        assertThrows(IllegalArgumentException.class, () -> {
            biMap.put("two", 1);
        });
    }

    @Test
    public void testRemoveByKey() {
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.put("one", 1);
        biMap.put("two", 2);
        
        // 测试根据key删除
        Integer removedValue = biMap.removeByKey("one");
        assertEquals(1, removedValue);
        assertNull(biMap.getValue("one"));
        assertNull(biMap.getKey(1));
        assertEquals(1, biMap.size());
    }

    @Test
    public void testRemoveByValue() {
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.put("one", 1);
        biMap.put("two", 2);
        
        // 测试根据value删除
        String removedKey = biMap.removeByValue(1);
        assertEquals("one", removedKey);
        assertNull(biMap.getKey(1));
        assertNull(biMap.getValue("one"));
        assertEquals(1, biMap.size());
    }

    @Test
    public void testContainsKeyAndValue() {
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.put("one", 1);
        
        // 测试包含检查
        assertTrue(biMap.containsKey("one"));
        assertTrue(biMap.containsValue(1));
        assertFalse(biMap.containsKey("two"));
        assertFalse(biMap.containsValue(2));
    }

    @Test
    public void testSizeAndEmpty() {
        BiMap<String, Integer> biMap = new BiMap<>();
        
        // 测试空映射
        assertTrue(biMap.isEmpty());
        assertEquals(0, biMap.size());
        
        // 添加元素后测试
        biMap.put("one", 1);
        assertFalse(biMap.isEmpty());
        assertEquals(1, biMap.size());
        
        // 清空后测试
        biMap.clear();
        assertTrue(biMap.isEmpty());
        assertEquals(0, biMap.size());
    }

    @Test
    public void testKeySetAndValues() {
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.put("one", 1);
        biMap.put("two", 2);
        
        // 测试获取键和值的集合
        assertTrue(biMap.keySet().contains("one"));
        assertTrue(biMap.keySet().contains("two"));
        assertTrue(biMap.values().contains(1));
        assertTrue(biMap.values().contains(2));
    }
}