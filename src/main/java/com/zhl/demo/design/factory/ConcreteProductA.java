package com.zhl.demo.design.factory;

/**
 * 具体产品A
 */
public class ConcreteProductA implements Product {
    @Override
    public void display() {
        System.out.println("ConcreteProductA display method");
    }
}