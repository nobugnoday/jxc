package com.atguigu.jxc.controller;

import com.atguigu.jxc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/unit")
public class UnitController {


    @Autowired
    private UnitService unitService;


    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(){
        return unitService.list();
    }

}
