package com.client;

import command.ICommand;
import eventHandler.SubList;
import eventManager.MessageManager;
import eventSource.Department;
import pojo.Message;
import pojo.Student;
import pojo.Subscriber;
import pojo.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Client {
    private final static Map<String,Department> departments = new HashMap<>(); //存储部门
    private final static Map<String,Subscriber> subscribers = new HashMap<>(); //存储订阅者
    private final static Map<String,SubList> subLists = new HashMap<>(); //存储订阅者名单
    private final static MessageManager messageManager = new MessageManager(); //消息管理器
    private final static Command command = new Command(); //指令系统
    public static void main(String[] args)
    {
        System.out.println("======欢迎使用消息管理系统！======");
        System.out.println("===指令介绍请参见README.md文件===");
        System.out.println("请输入指令进行操作(exit退出程序)...");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            // 退出条件
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("退出系统！");
                break;
            } else if (!input.equalsIgnoreCase("")) {
                // 非空，解析并执行指令
                command.read(input);
                command.parse();
            }
            else {
                System.out.println("-------------------");
            }
        }
        scanner.close();
    }

    // v1.0版本同步发送通知 的 运行示例
    // 直接调用即可
    private static void runExample()
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

    // 指令系统
    private static class Command implements ICommand
    {
        private String command; // 读取到的指令
        private final List<String> regex = new ArrayList<>(); //指令格式

        public Command()
        {
            // 指令格式
            // 创建类指令
            regex.add("create dep \\[([^\\]]+)\\]"); // 0.创建部门
            regex.add("create stu \\[([^\\]]+)\\]"); // 1.创建学生
            regex.add("create tea \\[([^\\]]+)\\]"); // 2.创建老师
            regex.add("create subs \\[([^\\]]+)\\]"); // 3.创建订阅者名单
            // 添加类指令
            regex.add("add \\[([^\\]]+)\\] \\[([^\\]]+)\\]"); // 4.向订阅者名单中添加订阅者
            // 注册类指令
            regex.add("register \\[([^\\]]+)\\] \\[([^\\]]+)\\]"); // 5.向部门中注册订阅者名单
            // 展示类指令
            regex.add("show subs \\[([^\\]]+)\\]"); // 6.展示订阅者名单
            regex.add("show submes \\[([^\\]]+)\\]"); // 7.展示订阅者的消息
            // 查看类指令
            regex.add("check \\[([^\\]]+)\\] (\\d+)"); // 8.查看订阅者的消息
            // 发送类指令
            regex.add("send \\[([^\\]]+)\\] \\[([^\\]]+)\\] \\[([^\\]]+)\\]"); // 9.部门发送消息(标题加内容)

        }

        @Override
        public void read(String command) {
            this.command = command;
        }

        @Override
        public void parse() {
            // 遍历regex，匹配指令
            int index = 0;
            for (String r :regex)
            {
                Pattern pattern = Pattern.compile(r);
                Matcher matcher = pattern.matcher(command);
                if(matcher.find()) {
                    //匹配成功，执行相应的指令
                    switch (index) {
                        case 0: // 创建部门
                            executeCreate(0, matcher.group(1));
                            return;
                        case 1: // 创建学生
                            executeCreate(1, matcher.group(1));
                            return;
                        case 2: // 创建老师
                            executeCreate(2, matcher.group(1));
                            return;
                        case 3: // 创建订阅者名单
                            executeCreate(3, matcher.group(1));
                            return;
                        case 4: // 向订阅者名单中添加订阅者
                            executeAdd(matcher.group(1), matcher.group(2));
                            return;
                        case 5: // 向部门中注册订阅者名单
                            executeRegister(matcher.group(1), matcher.group(2));
                            return;
                        case 6: // 展示订阅者名单
                            executeShow(0, matcher.group(1));
                            return;
                        case 7: // 展示订阅者的消息
                            executeShow(1, matcher.group(1));
                            return;
                        case 8: // 查看订阅者的消息
                            executeCheck(matcher.group(1), Integer.parseInt(matcher.group(2))-1);
                            return;
                        case 9: // 部门发送消息
                            executeSend(matcher.group(1), matcher.group(2), matcher.group(3));
                            return;
                    }
                }
                index++;
            }
            System.out.println("指令格式错误！");
        }

        @Override
        public void executeCreate(int type, String name) {
            switch (type) {
                case 0: // 创建部门
                    Department department = new Department(name);
                    departments.put(name, department);
                    messageManager.addSource(department);
                    System.out.println("创建部门" + name + "成功！");
                    break;
                case 1: // 创建学生
                    Student student = new Student(name);
                    subscribers.put(name, student);
                    System.out.println("创建学生" + name + "成功！");
                    break;
                case 2: // 创建老师
                    Teacher teacher = new Teacher(name);
                    subscribers.put(name, teacher);
                    System.out.println("创建老师" + name + "成功！");
                    break;
                case 3: // 创建订阅者名单
                    SubList subList = new SubList(name);
                    subLists.put(name, subList);
                    System.out.println("创建订阅者名单" + name + "成功！");
                    break;
            }
        }

        @Override
        public void executeAdd(String p1, String p2) {
            // 找到订阅者名单
            SubList subList = subLists.get(p1);
            if(subList == null) {
                System.out.println("订阅者名单" + p1 + "不存在！");
                return;
            }
            // 找到订阅者
            Subscriber subscriber = subscribers.get(p2);
            if(subscriber == null) {
                System.out.println("订阅者" + p2 + "不存在！");
                return;
            }
            // 添加订阅者
            subList.attachSubscriber(subscriber);
            System.out.println("订阅者" + p2 + "添加到订阅者名单" + p1 + "成功！");
        }


        @Override
        public void executeShow(int type, String name) {
            switch (type) {
                case 0: // 展示订阅者名单
                    SubList subList = subLists.get(name);
                    if (subList == null) {
                        System.out.println("订阅者名单" + name + "不存在！");
                        return;
                    }
                    System.out.println("订阅者名单" + name + "包含以下订阅者：");
                    subList.showSubscribers();
                    break;
                case 1: // 展示订阅者的消息
                    Subscriber subscriber = subscribers.get(name);
                    if (subscriber == null) {
                        System.out.println("订阅者" + name + "不存在！");
                        return;
                    }
                    System.out.println("订阅者" + name + "的消息如下：");
                    subscriber.showMessages();
                    break;
            }

        }

        @Override
        public void executeCheck(String name, int index) {
            Subscriber subscriber = subscribers.get(name);
            if (subscriber == null) {
                System.out.println("订阅者" + name + "不存在！");
                return;
            }
            subscriber.checkMessage(index);
        }

        @Override
        public void executeRegister(String p1, String p2) {
            Department department = departments.get(p1);
            if(department == null) {
                System.out.println("部门" + p1 + "不存在！");
                return;
            }
            SubList subList = subLists.get(p2);
            if(subList == null) {
                System.out.println("订阅者名单" + p2 + "不存在！");
                return;
            }
            messageManager.addSubs(department, subList);
            System.out.println("订阅者名单" + p2 + "注册到部门" + p1 + "成功！");

        }

        @Override
        public void executeSend(String source, String title, String content) {
            Department department = departments.get(source);
            if(department == null) {
                System.out.println("部门" + source + "不存在！");
                return;
            }
            department.createMessage(title, content);
            // 支持多线程操作
            Thread thread = new Thread(department, department.getName());
            thread.start();
        }
    }



}