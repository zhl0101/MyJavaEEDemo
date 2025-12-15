package com.zhl.demo;

import java.util.Collection;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 双向映射数据结构，支持通过key查找value，也支持通过value查找key
 * @param <K> key的类型
 * @param <V> value的类型
 */
public class BiMap<K, V> {
    private Map<K, V> keyToValueMap;
    private Map<V, K> valueToKeyMap;

    /**
     * 构造函数
     */
    public BiMap() {
        this.keyToValueMap = new HashMap<>();
        this.valueToKeyMap = new HashMap<>();
    }

    /**
     * 添加键值对
     * @param key 键
     * @param value 值
     * @throws IllegalArgumentException 如果key或value已存在
     */
    public void put(K key, V value) {
        if (keyToValueMap.containsKey(key)) {
            throw new IllegalArgumentException("Key already exists: " + key);
        }
        
        if (valueToKeyMap.containsKey(value)) {
            throw new IllegalArgumentException("Value already exists: " + value);
        }
        
        keyToValueMap.put(key, value);
        valueToKeyMap.put(value, key);
    }

    /**
     * 根据键获取值
     * @param key 键
     * @return 对应的值，如果不存在则返回null
     */
    public V getValue(K key) {
        return keyToValueMap.get(key);
    }

    /**
     * 根据值获取键
     * @param value 值
     * @return 对应的键，如果不存在则返回null
     */
    public K getKey(V value) {
        return valueToKeyMap.get(value);
    }

    /**
     * 移除指定键的映射
     * @param key 要移除的键
     * @return 被移除的值，如果键不存在则返回null
     */
    public V removeByKey(K key) {
        V value = keyToValueMap.remove(key);
        if (value != null) {
            valueToKeyMap.remove(value);
        }
        return value;
    }

    /**
     * 移除指定值的映射
     * @param value 要移除的值
     * @return 被移除的键，如果值不存在则返回null
     */
    public K removeByValue(V value) {
        K key = valueToKeyMap.remove(value);
        if (key != null) {
            keyToValueMap.remove(key);
        }
        return key;
    }

    /**
     * 检查是否包含指定的键
     * @param key 要检查的键
     * @return 如果包含该键返回true，否则返回false
     */
    public boolean containsKey(K key) {
        return keyToValueMap.containsKey(key);
    }

    /**
     * 检查是否包含指定的值
     * @param value 要检查的值
     * @return 如果包含该值返回true，否则返回false
     */
    public boolean containsValue(V value) {
        return valueToKeyMap.containsKey(value);
    }

    /**
     * 获取所有键的集合
     * @return 所有键的集合
     */
    public Set<K> keySet() {
        return keyToValueMap.keySet();
    }

    /**
     * 获取所有值的集合
     * @return 所有值的集合
     */
    public Collection<V> values() {
        return keyToValueMap.values();
    }

    /**
     * 获取键值对的数量
     * @return 映射的数量
     */
    public int size() {
        return keyToValueMap.size();
    }

    /**
     * 判断映射是否为空
     * @return 如果映射为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return keyToValueMap.isEmpty();
    }

    /**
     * 清空所有映射
     */
    public void clear() {
        keyToValueMap.clear();
        valueToKeyMap.clear();
    }

    @Override
    public String toString() {
        return keyToValueMap.toString();
    }
}