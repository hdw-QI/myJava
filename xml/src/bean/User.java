package bean;

/**
 * @projectName: three stage
 * @package: bean
 * @className: User
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/23 12:56
 * @version: 1.0
 */
public class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
