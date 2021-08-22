package com.wanggl.h2sql.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.wanggl.h2sql.base.page.PageReq;
import com.wanggl.h2sql.base.page.PageResp;
import com.wanggl.h2sql.base.resp.ApiResp;
import com.wanggl.h2sql.entity.User;
import com.wanggl.h2sql.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "1:用户测试")
@ApiSort(1)
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "1:分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("page")
    public ApiResp<PageResp<User>> page(@RequestBody PageReq<User> pageReq) {
        return ApiResp.ofSuccess(userService.page(pageReq));
    }

    @ApiOperation(value = "2:保存")
    @ApiOperationSupport(order = 2)
    @PostMapping("save")
    public ApiResp<User> save(@RequestBody User user) {
        return ApiResp.ofSuccess(userService.save(user));
    }
}
