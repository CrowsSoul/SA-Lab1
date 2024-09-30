package eventSource;

import eventManager.MessageManager;
import pojo.Message;

/**
 * 消息源
 * 实现IMessageSource接口，提供发送消息和创建消息的方法
 * 实现Runnable接口，支持异步发送消息
 */
public class MessageSource implements IMessageSource,Runnable {
    private final String name; //部门的名称
    private Message message; //待发送的消息

    @Override
    public void sendMessage(Message message) {
        MessageManager manager = new MessageManager();
        manager.receiveMessage(message);
        manager.distributeMessage(message);
    }

    @Override
    public Message createMessage(String title, String content) {
        message = new Message(content,name,title);
        return message;
    }

    /**
     * 获取消息源名称
     * @return 消息源名称
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 构造函数
     * @param name 消息源名称
     */
    public MessageSource(String name) {
        this.name = name;
    }

    /**
     * 用于线程中，异步发送已创建的消息
     */
    @Override
    public void run() {
        if(message!= null) {
            sendMessage(message);
            System.out.println("消息发送成功！");
        }
    }
}
