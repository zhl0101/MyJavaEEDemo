package com.zhl.demo.factory;

/**
 * 具体工厂A
 */
public class ConcreteFactoryA implements FactoryMethod {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}