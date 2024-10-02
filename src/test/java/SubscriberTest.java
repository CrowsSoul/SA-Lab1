import org.testng.annotations.Test;
import pojo.Message;
import pojo.Subscriber;
import static org.junit.Assert.assertEquals;

public class SubscriberTest {

    @Test
    public void receiveTest(){
        Subscriber subscriber = new Subscriber("john");
        Message m1 = new Message("1","1","1");
        Message m2 = new Message("2","2","2");
        Message m3 = new Message("3","3","3");
        subscriber.receiveMessage(m1);
        subscriber.receiveMessage(m2);
        subscriber.receiveMessage(m3);
        assertEquals(subscriber.getMessages().size(),3);
    }

    @Test
    public void getNameTest(){
        Subscriber subscriber1 = new Subscriber("john");
        assertEquals(subscriber1.getName(),"john");
        Subscriber subscriber2 = new Subscriber("jack");
        assertEquals(subscriber2.getName(),"jack");
    }
}
