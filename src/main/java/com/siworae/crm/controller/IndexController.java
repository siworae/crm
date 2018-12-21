package com.siworae.crm.controller;

import com.siworae.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController extends BaseController {

    /**
     * 登陆界面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

}
