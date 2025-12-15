package com.zhl.demo.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;

/**
 * 担心链表
 */
@Slf4j
public class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
        this.next = null;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 反转单向链表
     *
     * @return
     */
    public static Node revrseNode(Node head) {
        Node node1 = null;
        Node curr = head;

        while (curr != null) {
            Node temp = curr.next;
            curr.next = node1;
            node1 = curr;
            curr = temp;
        }
        return node1;

    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);

        // 使用新添加的展示方法
        System.out.println("反转前:");
        displayNode(n1);
        System.out.println("字符串形式: " + nodeToString(n1));

        Node node = revrseNode(n1);

        System.out.println("反转后:");
        displayNode(node);
        System.out.println("字符串形式: " + nodeToString(node));
    }

    /**
     * 展示链表节点
     *
     * @param head 头节点
     */
    public static void displayNode(Node head) {
        Node current = head;
        System.out.print("链表内容: ");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 将链表转换为字符串形式展示
     *
     * @param head 头节点
     * @return 链表的字符串表示
     */
    public static String nodeToString(Node head) {
        if (head == null) {
            return "空链表";
        }

        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }


//    public static void main(String[] args) {
//        Node n1 = new Node(1);
//        n1.next = new Node(2);
//        n1.next.next = new Node(3);
//        n1.next.next.next = new Node(4);
//        log.info("反转前：{}", JSON.toJSONString(n1));
//        Node node = revrseNode(n1);
//        log.info("反转后：{}",JSON.toJSONString(node));
//    }

}
