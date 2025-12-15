package com.zhl.demo.chain;

/**
 * 总经理处理者 - 处理15天以内的请假和任意金额的报销
 */
public class GeneralManagerHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == Request.Type.LEAVE && request.getAmount() <= 15) {
            System.out.println("总经理批准了您的请假申请: " + request.getContent());
        } else if (request.getType() == Request.Type.REIMBURSEMENT) {
            System.out.println("总经理批准了您的报销申请: " + request.getContent() + "，金额: " + request.getAmount());
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