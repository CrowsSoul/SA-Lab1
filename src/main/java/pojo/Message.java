package pojo;

/**
 * 消息实体类
 */
public class Message {
    private final String content;
    private final String source;
    private final String title;

    /**
     * 构造方法
     * @param content 内容
     * @param source 消息的发送者
     * @param title 标题
     */
    public Message(String content, String source, String title)
    {
        this.content = content;
        this.source = source;
        this.title = title;
    }

    /**
     * 展示消息
     */
    public void show()
    {
        System.out.println("===标题："+title+"===");
        System.out.println(content);
        System.out.println("===by "+ source +"===");
    }

    /**
     * 获取标题
     * @return 标题
     */
    public String getTitle()
    {
        return this.title;
    }
}
