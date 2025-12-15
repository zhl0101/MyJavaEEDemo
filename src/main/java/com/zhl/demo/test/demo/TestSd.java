package com.zhl.demo.test.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class TestSd {
    public String name;

//    public TestSd() {
//        System.out.println("无参构造");
//    }
//
//    public TestSd(String name) {
//        this.name = name;
//        System.out.println("有参构造");
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }

//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }

    public boolean equals2(Object obj) {
        return super.equals(obj);
    }

    public static void main(String[] args) {

        TestSd testSd = new TestSd();
        testSd.setName("zhl");
        TestSd testSd1 = new TestSd();
        testSd1.setName("zhl");
        System.out.println(testSd == testSd1);
        System.out.println(testSd.equals(testSd1));
    }
}
