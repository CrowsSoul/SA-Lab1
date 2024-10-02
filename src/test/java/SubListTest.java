import eventHandler.SubList;
import org.testng.annotations.Test;
import pojo.ISubscriber;
import pojo.Message;
import pojo.Student;
import pojo.Teacher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SubListTest {

    @Test
    public void getName_Test(){
        SubList cs_List = new SubList("cs_list");
        assertEquals(cs_List.getName(),"cs_list");
    }

    @Test
    public void attach_Test(){
        SubList cs_List = new SubList("cs_list");
        Student s1 =new Student("student1");
        Student s2 =new Student("student2");
        Teacher t1 = new Teacher("teacher1");
        Teacher t2 = new Teacher("teacher2");
        cs_List.attachSubscriber(s1);
        cs_List.attachSubscriber(s2);
        cs_List.attachSubscriber(t1);
        cs_List.attachSubscriber(t2);
        List<ISubscriber> subscriber= new ArrayList<>();
        subscriber.add(s1);
        subscriber.add(s2);
        subscriber.add(t1);
        subscriber.add(t2);
        assertEquals(cs_List.getsubscribers(),subscriber);
    }

    @Test
    public void detach_Test(){
        SubList cs_List = new SubList("cs_list");
        Student s1 =new Student("student1");
        Student s2 =new Student("student2");
        Teacher t1 = new Teacher("teacher1");
        Teacher t2 = new Teacher("teacher2");
        cs_List.attachSubscriber(s1);
        cs_List.attachSubscriber(s2);
        cs_List.attachSubscriber(t1);
        cs_List.attachSubscriber(t2);
        List<ISubscriber> subscriber= new ArrayList<>();
        subscriber.add(s1);
        subscriber.add(s2);
        subscriber.add(t1);
        cs_List.detachSubscriber(t2);
        assertEquals(cs_List.getsubscribers(),subscriber);
    }

    @Test
    public void receive_Test(){
        SubList cs_List = new SubList("cs_list");
        SubList tc_List = new SubList("tc_list");
        Student s1 =new Student("student1");
        Student s2 =new Student("student2");
        Teacher t1 = new Teacher("teacher1");
        Teacher t2 = new Teacher("teacher2");
        Message m1 = new Message("1","1","1");
        Message m2 = new Message("2","2","2");
        cs_List.attachSubscriber(s1);
        cs_List.attachSubscriber(t1);
        tc_List.attachSubscriber(s2);
        tc_List.attachSubscriber(t2);
        cs_List.receiveMessage(m1);
        tc_List.receiveMessage(m2);
        assertEquals(s1.getMessages().get(0).getTitle(),"1");
        assertEquals(t1.getMessages().get(0).getTitle(),"1");
        assertEquals(s2.getMessages().get(0).getTitle(),"2");
        assertEquals(t2.getMessages().get(0).getTitle(),"2");
    }
}
