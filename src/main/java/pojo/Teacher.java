package pojo;

/**
 * 教师实体类
 */
public class Teacher extends Subscriber {
    private String academy = "None";// 教师所属学院
    private String title = "None";// 教师职称

    /**
     * 构造方法
     *
     * @param name 教师姓名
     */
    public Teacher(String name) {
        super(name);
    }
}
