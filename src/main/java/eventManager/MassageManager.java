package eventManager;

import eventHandler.ISubList;
import eventHandler.SubList;
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
public class MassageManager implements IMessageManager {
    private final List<Message> messages = new ArrayList<Message>(); // 消息列表
    private final Map<String, List<ISubList>> relations = new HashMap<String, List<ISubList>>(); // 订阅关系

    @Override
    public void receiveMessage(Message message) {

    }

    @Override
    public void distributeMessage(Message message) {

    }

    @Override
    public void addSource(IMessageSource source) {

    }

    @Override
    public void addSubs(String source, ISubList subs) {

    }
}
