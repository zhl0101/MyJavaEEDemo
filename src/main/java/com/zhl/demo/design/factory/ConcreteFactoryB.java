package com.zhl.demo.design.factory;

/**
 * 具体工厂B
 */
public class ConcreteFactoryB implements FactoryMethod {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}