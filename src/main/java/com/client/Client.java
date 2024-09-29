package com.client;

import eventHandler.SubList;
import eventManager.MessageManager;
import eventSource.Department;
import pojo.Message;
import pojo.Student;
import pojo.Teacher;

public class Client {
    public static void main(String[] args)
    {
        // 首先对订阅者进行初始化
        Teacher t1 = new Teacher("Master Lee");
        Teacher t2 = new Teacher("Doctor Wang");
        Student s1 = new Student("Jack");
        Student s2 = new Student("Jane");
        Student s3 = new Student("Mary");
        Student s4 = new Student("Lucy");

        // 创建各个部门
        Department cs = new Department("CS"); // 计算机学院
        Department tc = new Department("TC"); // 电信学院
        Department ao = new Department("AO"); // 教务处

        // 创建各订阅者名单
        SubList csList = new SubList("CSList");
        csList.attachSubscriber(t1);
        csList.attachSubscriber(s1);
        csList.attachSubscriber(s3);

        SubList tcList = new SubList("TCList");
        tcList.attachSubscriber(t2);
        tcList.attachSubscriber(s2);
        tcList.attachSubscriber(s4);

        SubList aoList = new SubList("AOList");
        aoList.attachSubscriber(t1);
        aoList.attachSubscriber(t2);
        aoList.attachSubscriber(s1);
        aoList.attachSubscriber(s2);
        aoList.attachSubscriber(s3);
        aoList.attachSubscriber(s4);

        // 初始化消息管理器，并注册各个部门
        MessageManager messageManager = new MessageManager();
        messageManager.addSource(cs);
        messageManager.addSource(tc);
        messageManager.addSource(ao);

        // 向各个部门注册订阅者名单
        messageManager.addSubs(cs, csList);
        messageManager.addSubs(tc, tcList);
        messageManager.addSubs(ao, aoList);

        // 模拟场景
        System.out.println("*计算机学院发布了新通知！");
        // 创建并发送通知
        Message m1 = cs.createMessage("ICPC 赛事报名","2024年10月8日到理学楼501报名");
        cs.sendMessage(m1);
        // Lee老师和Jack、Mary同学收到通知

        System.out.println("*电信学院发布了新通知！");
        // 创建并发送通知
        Message m2 = tc.createMessage("电信学院欢庆国庆","有兴趣的同学可报名升旗仪式");
        tc.sendMessage(m2);
        // Wang老师和Jane、Lucy同学收到通知

        System.out.println("*教务处发布了新通知！");
        // 创建并发送通知
        Message m3 = ao.createMessage("选课系统故障","请遇到问题的师生尽快联系教务处");
        ao.sendMessage(m3);
        // Lee老师、Wang老师、Jack、Jane、Mary、Lucy同学收到通知

        System.out.println("*Lee老师查看自己的消息...");
        t1.showMessages(); // 显示Lee老师的消息
        System.out.println("*选择ICPC赛事报名进行查看...");
        t1.checkMessage(0);

        System.out.println("*Lucy同学查看自己的消息...");
        s4.showMessages(); // 显示Lee老师的消息
        System.out.println("*选择欢庆国庆消息进行查看...");
        s4.checkMessage(0);

        System.out.println("*Jack同学查看自己的消息...");
        s1.showMessages(); // 显示Jack同学的消息
        System.out.println("*选择选课系统故障消息进行查看...");
        s1.checkMessage(1);


    }

}