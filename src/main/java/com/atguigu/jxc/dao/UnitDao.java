package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Unit;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UnitDao {

    List<Unit> listAll();
}
