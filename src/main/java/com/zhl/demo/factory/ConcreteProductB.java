package com.zhl.demo.factory;

/**
 * 具体产品B
 */
public class ConcreteProductB implements Product {
    @Override
    public void display() {
        System.out.println("ConcreteProductB display method");
    }
}