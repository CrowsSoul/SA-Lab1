import eventHandler.SubList;
import eventManager.MessageManager;
import eventSource.Department;
import eventSource.MessageSource;
import org.testng.annotations.Test;
import pojo.Message;
import pojo.Student;
import pojo.Teacher;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class MessageManagerTest {
    @Test
    public void receiveTest(){
        MessageManager messageManager = new MessageManager();
        Message m1 = new Message("1","1","1");
        Message m2 = new Message("2","2","2");
        Message m3 = new Message("3","3","3");
        messageManager.receiveMessage(m1);
        messageManager.receiveMessage(m2);
        messageManager.receiveMessage(m3);
        List<Message> messageList =new ArrayList<>();
        messageList.add(m1);
        messageList.add(m2);
        messageList.add(m3);
        assertEquals(messageManager.getMessages(),messageList);
    }

    @Test
    public void distributeTest(){
        MessageManager messageManager = new MessageManager();
        Message m1 = new Message("1","cs","1");
        Message m2 = new Message("2","tc","2");
        Department cs = new Department("cs");
        Department tc = new Department("tc");
        SubList cs_List = new SubList("cs_list");
        SubList tc_List = new SubList("tc_list");
        Student s1 =new Student("student1");
        Student s2 =new Student("student2");
        Teacher t1 = new Teacher("teacher1");
        Teacher t2 = new Teacher("teacher2");
        cs_List.attachSubscriber(s1);
        cs_List.attachSubscriber(t1);
        tc_List.attachSubscriber(s2);
        tc_List.attachSubscriber(t2);
        messageManager.addSource(cs);
        messageManager.addSource(tc);
        messageManager.addSubs(cs,cs_List);
        messageManager.addSubs(tc,tc_List);
        messageManager.distributeMessage(m1);
        messageManager.distributeMessage(m2);
        assertEquals(s1.getMessages().get(0),m1);
        assertEquals(t1.getMessages().get(0),m1);
        assertEquals(s2.getMessages().get(0),m2);
        assertEquals(t2.getMessages().get(0),m2);
    }

    @Test
    public void addSourceTest(){
        MessageManager messageManager = new MessageManager();
        Department cs = new Department("cs");
        Department tc = new Department("tc");
        messageManager.addSource(cs);
        messageManager.addSource(tc);
        assertEquals(messageManager.getRelations().keySet().size(),2);
    }

    @Test
    public void addSubsTest(){
        MessageManager messageManager = new MessageManager();
        Department cs = new Department("cs");
        Department tc = new Department("tc");
        SubList cs_List = new SubList("cs_list");
        SubList tc_List = new SubList("tc_list");
        messageManager.addSource(cs);
        messageManager.addSource(tc);
        messageManager.addSubs(cs,cs_List);
        messageManager.addSubs(tc,tc_List);
        assertEquals(messageManager.getRelations().get("cs").size(),1);
        assertEquals(messageManager.getRelations().get("tc").size(),1);
        assertEquals(messageManager.getRelations().values().size(),2);
    }
}
