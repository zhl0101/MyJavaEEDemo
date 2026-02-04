package com.zhl.demo.design.factory;

/**
 * 抽象工厂示例 - 数据库操作相关接口和类
 */

// 数据库连接接口
interface Connection {
    void connect();
}

// MySQL连接实现
class MySqlConnection implements Connection {
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL database");
    }
}

// Oracle连接实现
class OracleConnection implements Connection {
    @Override
    public void connect() {
        System.out.println("Connecting to Oracle database");
    }
}

// 命令接口
interface Command {
    void execute();
}

// MySQL命令实现
class MySqlCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Executing MySQL command");
    }
}

// Oracle命令实现
class OracleCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Executing Oracle command");
    }
}

// 抽象工厂接口
public interface DatabaseFactory {
    Connection createConnection();
    Command createCommand();
}

// MySQL工厂实现
class MySqlFactory implements DatabaseFactory {
    @Override
    public Connection createConnection() {
        return new MySqlConnection();
    }
    
    @Override
    public Command createCommand() {
        return new MySqlCommand();
    }
}

// Oracle工厂实现
class OracleFactory implements DatabaseFactory {
    @Override
    public Connection createConnection() {
        return new OracleConnection();
    }
    
    @Override
    public Command createCommand() {
        return new OracleCommand();
    }
}