package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.UnitDao;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitDao unitDao;

    @Override
    public Map<String, Object> list() {
        Map<String ,Object> map = new HashMap<>();
        List<Unit> unitList = unitDao.listAll();
        map.put("rows",unitList);
        return map;
    }
}
