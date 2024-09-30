package command;

/**
 * 指令处理接口
 * 用于接收指令并进行处理
 */
public interface ICommand {

    /**
     * 读取指令
     * @param command 待读取的指令
     */
    void read(String command);

    /**
     * 解析已读取的指令
     */
    void parse();

    /**
     * 执行创建类型的指令
     * @param type 类型编号
     * @param name 名称
     */
    void executeCreate(int type,String name);

    /**
     * 执行添加类型的指令
     * @param p1 添加目标
     * @param p2 被添加者
     */
    void executeAdd(String p1,String p2);

    /**
     * 执行展示类型的指令
     * @param type 类型编号
     * @param name 名称
     */
    void executeShow(int type,String name);

    /**
     * 执行查看类型的指令
     * @param name 所有者
     * @param index 被查看物的序号
     */
    void executeCheck(String name,int index);

    /**
     * 执行注册类型的指令
     * @param p1 注册目标
     * @param p2 被注册者
     */
    void executeRegister(String p1,String p2);

    /**
     * 执行发送类型的指令
     * @param source 发送者
     * @param title 标题
     * @param content 内容
     */
    void executeSend(String source,String title,String content);


}
