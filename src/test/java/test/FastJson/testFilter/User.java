package test.FastJson.testFilter;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/30
 * \* Time: 下午4:23
 * \* Description:
 * \
 */
public class User {
    private long id;
    private String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
