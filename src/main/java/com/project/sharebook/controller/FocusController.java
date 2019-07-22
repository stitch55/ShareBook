package com.project.sharebook.controller;

import com.project.sharebook.response.AjaxData;
import com.project.sharebook.service.UserService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//处理与关注相关的数据
@RestController
public class FocusController extends BaseController {
    @Autowired
    UserService userService;

}
