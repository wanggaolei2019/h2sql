package com.wanggl.h2sql.controller;

import com.wanggl.h2sql.H2sqlApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    @GetMapping("shutdown")
    void shutdown() {
        H2sqlApplication.shutdown();
    }
}
