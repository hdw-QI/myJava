package ioc.xml.domain;

/**
 * @projectName: git
 * @package: ioc.xml.domain
 * @className: TestProperty
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/21 18:15
 * @version: 1.0
 */
public class TestProperty {
    private String name;

    public TestProperty() {
    }

    public TestProperty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestProperty{" +
                "name='" + name + '\'' +
                '}';
    }
}
