package com.wanggl.h2sql;

import com.wanggl.h2sql.entity.User;
import com.wanggl.h2sql.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class H2sqlApplicationTests {

    @Resource
    UserRepository userRepository;

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("wanggl");
        userRepository.save(user);
    }

}
