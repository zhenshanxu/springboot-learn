package com.example.springbootlearn.ctrl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Project springboot-learn
 * @Description 静态索引控制
 * @Author xuzhenshan
 * @Date 2023/12/28 10:07:02
 * @Version 1.0
 */
public class IndexCtrl {

    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        System.out.println("页面重定向");
        return "build:index";
    }

}
