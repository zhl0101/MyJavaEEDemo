package com.zhl.demo.design.factory;

/**
 * 简单工厂模式
 */
public class SimpleFactory {
    public static Product createProduct(String type) {
        if ("A".equals(type)) {
            return new ConcreteProductA();
        } else if ("B".equals(type)) {
            return new ConcreteProductB();
        } else {
            throw new IllegalArgumentException("Unknown product type: " + type);
        }
    }
}