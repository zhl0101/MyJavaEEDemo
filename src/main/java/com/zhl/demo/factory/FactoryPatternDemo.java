package com.zhl.demo.factory;

/**
 * 工厂模式演示类
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== 工厂模式演示 ===");
        
        // 简单工厂模式演示
        System.out.println("\n--- 简单工厂模式 ---");
        Product productA = SimpleFactory.createProduct("A");
        productA.display();
        
        Product productB = SimpleFactory.createProduct("B");
        productB.display();
        
        // 工厂方法模式演示
        System.out.println("\n--- 工厂方法模式 ---");
        FactoryMethod factoryA = new ConcreteFactoryA();
        Product prodA = factoryA.createProduct();
        prodA.display();
        
        FactoryMethod factoryB = new ConcreteFactoryB();
        Product prodB = factoryB.createProduct();
        prodB.display();
        
        // 抽象工厂模式演示
        System.out.println("\n--- 抽象工厂模式 ---");
        DatabaseFactory mysqlFactory = new MySqlFactory();
        Connection mysqlConnection = mysqlFactory.createConnection();
        Command mysqlCommand = mysqlFactory.createCommand();
        mysqlConnection.connect();
        mysqlCommand.execute();
        
        DatabaseFactory oracleFactory = new OracleFactory();
        Connection oracleConnection = oracleFactory.createConnection();
        Command oracleCommand = oracleFactory.createCommand();
        oracleConnection.connect();
        oracleCommand.execute();
    }
}