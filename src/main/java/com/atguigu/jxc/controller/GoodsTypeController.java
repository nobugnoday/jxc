package com.atguigu.jxc.controller;

import com.atguigu.jxc.service.GoodsTyepeServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTyepeServce goodsTyepeServce;


    /**
     * 3.1、查询商品所有分类
     */
    @RequestMapping("/loadGoodsType")
    public String loadGoodsType(){
        return goodsTyepeServce.loadGoodsType();
    }

}
