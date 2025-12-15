package com.zhl.demo.chain;

/**
 * 董事会处理者 - 处理所有请求
 */
public class BoardHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == Request.Type.LEAVE) {
            System.out.println("董事会批准了您的请假申请: " + request.getContent());
        } else if (request.getType() == Request.Type.REIMBURSEMENT) {
            System.out.println("董事会批准了您的报销申请: " + request.getContent() + "，金额: " + request.getAmount());
        } else {
            System.out.println("董事会批准了您的申请: " + request);
        }
    }
}