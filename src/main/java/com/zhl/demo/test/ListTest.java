package com.zhl.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
//        testArray2List();
        testList2Array();

    }

    //数组转List
    public static void testArray2List() {
        String[] strs = {"aaa", "bbb", "ccc"};
        List<String> list = Arrays.asList(strs);
        for (String s : list) {
            System.out.println(s);
        }
        strs[1] = "ddd";
        System.out.println("================");
        for (String s : list) {
            System.out.println(s);
        }
    }

    //list转数组
    public static void testList2Array() {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        String[] strs = list.toArray(new String[list.size()]);
        for (String s : strs) {
            System.out.println(s);
        }
        list.add("ddd");
        System.out.println("================");
        for (String s : strs) {
            System.out.println(s);
        }
    }
}
