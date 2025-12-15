package com.zhl.demo.chain;

/**
 * 经理处理者 - 处理7天以内的请假和5000元以内的报销
 */
public class ManagerHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == Request.Type.LEAVE && request.getAmount() <= 7) {
            System.out.println("经理批准了您的请假申请: " + request.getContent());
        } else if (request.getType() == Request.Type.REIMBURSEMENT && request.getAmount() <= 5000) {
            System.out.println("经理批准了您的报销申请: " + request.getContent() + "，金额: " + request.getAmount());
        } else {
            // 转发给下一个处理者
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            } else {
                System.out.println("申请未被批准: " + request);
            }
        }
    }
}