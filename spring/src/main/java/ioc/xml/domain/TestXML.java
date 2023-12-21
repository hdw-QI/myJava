package ioc.xml.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @projectName: git
 * @package: ioc.xml
 * @className: Test
 * @author: 胡代伟
 * @description: 测试是否注入成功
 * @date: 2023/12/21 18:00
 * @version: 1.0
 */
public class TestXML {
    private String name;
    private int age;

    private TestProperty testProperty;

    private int[] ins;

    private TestProperty[] testProperties;

    private List<Integer> list;

    private List<TestProperty> testPropertyList;

    private Map<Object, Object> map;

    public TestProperty getTestProperty() {
        return testProperty;
    }

    public void setTestProperty(TestProperty testProperty) {
        this.testProperty = testProperty;
    }

    public int[] getIns() {
        return ins;
    }

    public void setIns(int[] ins) {
        this.ins = ins;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

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

    public TestProperty[] getTestProperties() {
        return testProperties;
    }

    public void setTestProperties(TestProperty[] testProperties) {
        this.testProperties = testProperties;
    }

    public List<TestProperty> getTestPropertyList() {
        return testPropertyList;
    }

    public void setTestPropertyList(List<TestProperty> testPropertyList) {
        this.testPropertyList = testPropertyList;
    }

    public TestXML() {
    }

    public TestXML(String name) {
        this.name = name;
    }

    public TestXML(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestXML{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", testProperty=" + testProperty +
                ", ins=" + Arrays.toString(ins) +
                ", testProperties=" + Arrays.toString(testProperties) +
                ", list=" + list +
                ", testPropertyList=" + testPropertyList +
                ", map=" + map +
                '}';
    }
}
