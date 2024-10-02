import org.testng.annotations.Test;
import pojo.Message;
import static org.junit.Assert.assertEquals;

public class MessageTest {

    @Test
    public void getTitleTest(){
        Message m1 = new Message("1","2","3");
        Message m2 = new Message("4","5","6");
        Message m3 = new Message("7","8","9");
        assertEquals(m1.getTitle(),"3");
        assertEquals(m2.getTitle(),"6");
        assertEquals(m3.getTitle(),"9");
    }

    @Test
    public void getSourceTest(){
        Message m1 = new Message("1","2","3");
        Message m2 = new Message("4","5","6");
        Message m3 = new Message("7","8","9");
        assertEquals(m1.getSource(),"2");
        assertEquals(m2.getSource(),"5");
        assertEquals(m3.getSource(),"8");
    }
}
