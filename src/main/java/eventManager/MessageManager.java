package eventManager;

import eventHandler.ISubList;
import eventSource.IMessageSource;
import pojo.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息管理器的实现类
 * 实现消息管理器接口
 */
public class MessageManager implements IMessageManager {
    private final static List<Message> messages = new ArrayList<Message>(); // 消息列表
    private final static Map<String, List<ISubList>> relations = new HashMap<String, List<ISubList>>(); // 订阅关系

    /**
     * 获取消息列表
     * @return 消息集合
     */
    public List<Message> getMessages(){
        return  messages;
    }

    /**
     * 获取订阅关系列表
     * @return 订阅关系表
     */
    public Map<String, List<ISubList>> getRelations(){
        return  relations;
    }
    /**
     * 用于接收消息
     * 支持多线程调用
     * @param message 待接收的消息
     */
    @Override
    public synchronized void receiveMessage(Message message) {
        //接收到消息之后，首先需要将消息添加到消息列表中
        messages.add(message);
    }

    @Override
    public void distributeMessage(Message message) {
        // 遍历订阅关系，将消息分发给订阅者

        // 首先获取订阅者名单的列表
        List<ISubList> subLists = relations.get(message.getSource());
        // 遍历该列表，将消息发给订阅者名单
        for(ISubList subList: subLists)
        {
            subList.receiveMessage(message);
        }

    }

    @Override
    public void addSource(IMessageSource source) {
        //向Map中添加消息源
        relations.put(source.getName(), new ArrayList<ISubList>());
    }

    @Override
    public void addSubs(IMessageSource source, ISubList subs) {
        //向Map中添加订阅者
        List<ISubList> subLists = relations.get(source.getName());
        if(subLists!= null)
        {
            subLists.add(subs);
        }
        else {
            System.out.println("消息源不存在！");
        }
    }

}
