package com.zhl.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 如何统计一篇文章中每个单词出现的次数？
 */
@Slf4j
public class WordCount {

    public static Map<String, Integer> countWords(String text) {
        if (text == null || text.isEmpty()) {
            return new HashMap<>();
        }

        // 分割单词（简单的按空格分割，实际可能需要更复杂的分割）
        String[] words = text.split("\\s+");

        Map<String, Integer> wordCountMap = new HashMap<>();

        for (String word : words) {
            // 清理单词（去除标点，转小写）
            String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

            if (!cleanedWord.isEmpty()) {
                wordCountMap.put(cleanedWord, wordCountMap.getOrDefault(cleanedWord, 0) + 1);
            }
        }

        return wordCountMap;
    }

    // 使用Stream API
    public static Map<String, Long> countWordsWithStream(String text) {
        return Pattern.compile("\\s+")
                .splitAsStream(text)
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }


    public static void main(String[] args) {
        String words = "public class ConcurrentHashMapAnalysis {\n" +
                "    /**\n" +
                "     * ConcurrentHashMap特点：\n" +
                "     * 1. 线程安全的HashMap\n" +
                "     * 2. JDK1.7使用分段锁，JDK1.8使用CAS+synchronized\n" +
                "     * 3. 支持高并发访问\n" +
                "     * 4. 不允许null键和null值\n" +
                "     */\n" +
                "    \n" +
                "    // JDK1.8的ConcurrentHashMap实现\n" +
                "    public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> \n" +
                "        implements ConcurrentMap<K, V>, Serializable {\n" +
                "        \n" +
                "        // 数组，存储Node节点\n" +
                "        transient volatile Node<K, V>[] table;\n" +
                "        \n" +
                "        // 节点定义\n" +
                "        static class Node<K, V> implements Map.Entry<K, V> {\n" +
                "            final int hash;\n" +
                "            final K key;\n" +
                "            volatile V val;\n" +
                "            volatile Node<K, V> next;\n" +
                "            \n" +
                "            // 构造方法等...\n" +
                "        }\n" +
                "        \n" +
                "        // 使用CAS方式插入元素\n" +
                "        public V put(K key, V value) {\n" +
                "            return putVal(key, value, false);\n" +
                "        }\n" +
                "        \n" +
                "        final V putVal(K key, V value, boolean onlyIfAbsent) {\n" +
                "            if (key == null || value == null) throw new NullPointerException();\n" +
                "            int hash = spread(key.hashCode());\n" +
                "            int binCount = 0;\n" +
                "            for (Node<K, V>[] tab = table;;) {\n" +
                "                Node<K, V> f; \n" +
                "                int n, i, fh;\n" +
                "                if (tab == null || (n = tab.length) == 0)\n" +
                "                    tab = initTable();\n" +
                "                else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {\n" +
                "                    // 使用CAS插入新节点\n" +
                "                    if (casTabAt(tab, i, null, new Node<K, V>(hash, key, value, null)))\n" +
                "                        break;\n" +
                "                } else if ((fh = f.hash) == MOVED)\n" +
                "                    tab = helpTransfer(tab, f);\n" +
                "                else {\n" +
                "                    V oldVal = null;\n" +
                "                    synchronized (f) { // 对桶的头节点加锁\n" +
                "                        if (tabAt(tab, i) == f) {\n" +
                "                            if (fh >= 0) {\n" +
                "                                binCount = 1;\n" +
                "                                for (Node<K, V> e = f;; ++binCount) {\n" +
                "                                    K ek;\n" +
                "                                    if (e.hash == hash &&\n" +
                "                                        ((ek = e.key) == key ||\n" +
                "                                         (ek != null && key.equals(ek)))) {\n" +
                "                                        oldVal = e.val;\n" +
                "                                        if (!onlyIfAbsent)\n" +
                "                                            e.val = value;\n" +
                "                                        break;\n" +
                "                                    }\n" +
                "                                    Node<K, V> pred = e;\n" +
                "                                    if ((e = e.next) == null) {\n" +
                "                                        pred.next = new Node<K, V>(hash, key, value, null);\n" +
                "                                        break;\n" +
                "                                    }\n" +
                "                                }\n" +
                "                            } else if (f instanceof TreeBin) {\n" +
                "                                // 红黑树处理\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                    if (binCount != 0) {\n" +
                "                        if (binCount >= TREEIFY_THRESHOLD)\n" +
                "                            treeifyBin(tab, i);\n" +
                "                        if (oldVal != null)\n" +
                "                            return oldVal;\n" +
                "                        break;\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "            addCount(1L, binCount);\n" +
                "            return null;\n" +
                "        }\n" +
                "        \n" +
                "        // 使用CAS获取数组元素\n" +
                "        static final <K, V> Node<K, V> tabAt(Node<K, V>[] tab, int i) {\n" +
                "            return (Node<K, V>) U.getObjectVolatile(tab, ((long) i << ASHIFT) + ABASE);\n" +
                "        }\n" +
                "        \n" +
                "        // 使用CAS设置数组元素\n" +
                "        static final <K, V> boolean casTabAt(Node<K, V>[] tab, int i, Node<K, V> c, Node<K, V> v) {\n" +
                "            return U.compareAndSwapObject(tab, ((long) i << ASHIFT) + ABASE, c, v);\n" +
                "        }\n" +
                "    }\n" +
                "}";
        log.warn("1-时间");
        Map<String, Integer> stringIntegerMap = countWords(words);

        log.debug("1:{}", stringIntegerMap);
        log.warn("2-时间");
        Map<String, Long> stringLongMap = countWordsWithStream(words);
        log.debug("2:{}", stringLongMap);


    }


}
