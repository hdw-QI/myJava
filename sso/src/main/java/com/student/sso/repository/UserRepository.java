package com.student.sso.repository;

import com.student.sso.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 胡代伟
 * @date 2024-03-11 15:19
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    User getByUserNameAndUserPassword(String userName, String userPassword);
}
