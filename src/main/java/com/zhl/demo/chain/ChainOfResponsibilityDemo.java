package com.zhl.demo.chain;

/**
 * 责任链模式演示类
 */
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        System.out.println("=== 责任链模式演示 ===");
        
        // 创建处理者链
        Handler supervisor = new SupervisorHandler();
        Handler manager = new ManagerHandler();
        Handler generalManager = new GeneralManagerHandler();
        Handler board = new BoardHandler();
        
        // 设置责任链
        supervisor.setNextHandler(manager);
        manager.setNextHandler(generalManager);
        generalManager.setNextHandler(board);
        
        // 测试不同请求
        System.out.println("\n--- 请假审批测试 ---");
        Request leaveRequest1 = new Request(Request.Type.LEAVE, "病假一天", 1);
        supervisor.handleRequest(leaveRequest1);
        
        Request leaveRequest2 = new Request(Request.Type.LEAVE, "年假五天", 5);
        supervisor.handleRequest(leaveRequest2);
        
        Request leaveRequest3 = new Request(Request.Type.LEAVE, "长假二十天", 20);
        supervisor.handleRequest(leaveRequest3);
        
        System.out.println("\n--- 报销审批测试 ---");
        Request reimbursementRequest1 = new Request(Request.Type.REIMBURSEMENT, "办公用品购买", 500);
        supervisor.handleRequest(reimbursementRequest1);
        
        Request reimbursementRequest2 = new Request(Request.Type.REIMBURSEMENT, "差旅费用", 3000);
        supervisor.handleRequest(reimbursementRequest2);
        
        Request reimbursementRequest3 = new Request(Request.Type.REIMBURSEMENT, "大型设备采购", 10000);
        supervisor.handleRequest(reimbursementRequest3);
        
        // 日志处理链示例
        System.out.println("\n--- 日志处理链示例 ---");
        InfoLogger infoLogger = new InfoLogger(Logger.INFO);
        DebugLogger debugLogger = new DebugLogger(Logger.DEBUG);
        ErrorLogger errorLogger = new ErrorLogger(Logger.ERROR);
        
        // 设置日志责任链: ErrorLogger -> DebugLogger -> InfoLogger
        errorLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(infoLogger);
        
        System.out.println("处理INFO级别日志:");
        errorLogger.logMessage(Logger.INFO, "这是一条普通信息");
        
        System.out.println("\n处理DEBUG级别日志:");
        errorLogger.logMessage(Logger.DEBUG, "这是一个调试信息");
        
        System.out.println("\n处理ERROR级别日志:");
        errorLogger.logMessage(Logger.ERROR, "这是一个错误信息");
    }
}