package ioc.annotation.service;

import ioc.annotation.dao.TestDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @projectName: git
 * @package: ioc.annotation.service
 * @className: TestService
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/21 19:11
 * @version: 1.0
 */
@Service
public class TestService {
    @Resource
    private TestDao testDao;

    @Override
    public String toString() {
        return "TestService{" +
                "testDao=" + testDao +
                '}';
    }
}
