package ioc.annotation.dao;

import org.springframework.stereotype.Component;

/**
 * @projectName: git
 * @package: ioc.annotation.dao
 * @className: TestDao
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/21 19:12
 * @version: 1.0
 */
@Component
public class TestDao {
    private final String name = "test";

    @Override
    public String toString() {
        return "TestDao{" +
                "name='" + name + '\'' +
                '}';
    }
}
