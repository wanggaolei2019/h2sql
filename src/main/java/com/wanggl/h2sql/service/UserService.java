package com.wanggl.h2sql.service;

import com.wanggl.h2sql.base.page.PageReq;
import com.wanggl.h2sql.base.page.PageResp;
import com.wanggl.h2sql.entity.User;
import com.wanggl.h2sql.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserRepository userRepository;

    public PageResp<User> page(PageReq<User> pageReq) {
        Pageable page = pageReq.getPage();
        User params = pageReq.getParams();
        if (null == params) {
            params = new User();
        }
        Page<User> userPage = userRepository.findAll(Example.of(params), page);
        return PageResp.of(userPage);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
