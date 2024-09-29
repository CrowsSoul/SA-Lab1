package pojo;

/**
 * 学生实体类
 */
public class Student extends Subscriber {

    private int id = 0; // 学生ID
    private String academy = "None"; // 学生所属学院

    /**
     * 构造方法
     *
     * @param name 学生名称
     */
    public Student(String name) {
        super(name);
    }
}
