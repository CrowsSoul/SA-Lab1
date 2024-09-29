package eventSource;

import pojo.Message;

/**
 * 消息源
 * 实现IMessageSource接口，提供发送消息和创建消息的方法
 */
public class MessageSource implements IMessageSource {
    private final String name; //部门的名称

    @Override
    public void sendMessage(Message message) {

    }

    @Override
    public void createMessage(String title, String content) {

    }

    /**
     * 获取消息源名称
     * @return 消息源名称
     */
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
}
