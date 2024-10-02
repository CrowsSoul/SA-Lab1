import eventHandler.SubList;
import eventManager.MessageManager;
import eventSource.Department;
import org.testng.annotations.Test;
import pojo.Message;
import pojo.Student;
import pojo.Teacher;

import static org.junit.Assert.assertEquals;
public class MessageSourceTest {

    @Test
    public void createTest(){
        Department cs = new Department("cs");
        Message message = new Message("1","cs","1");
        assertEquals(cs.createMessage("1","1").getSource(),message.getSource());
        assertEquals(cs.createMessage("1","1").getTitle(),message.getTitle());
    }

    @Test
    public void sendTest(){
        MessageManager messageManager = new MessageManager();
        Department cs = new Department("cs");
        Department tc = new Department("tc");
        Message m1 = cs.createMessage("1","1");
        Message m2 = tc.createMessage("2","2");
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
        cs.sendMessage(m1);
        tc.sendMessage(m2);
        assertEquals(s1.getMessages().get(0),m1);
        assertEquals(t1.getMessages().get(0),m1);
        assertEquals(s2.getMessages().get(0),m2);
        assertEquals(t2.getMessages().get(0),m2);
    }

    @Test
    public void getNameTest(){
        Department cs = new Department("cs");
        Department tc = new Department("tc");
        assertEquals(cs.getName(),"cs");
        assertEquals(tc.getName(),"tc");
    }
}
