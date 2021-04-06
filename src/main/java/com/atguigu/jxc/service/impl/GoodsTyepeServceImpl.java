package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.GoodsTyepeServce;
import com.atguigu.jxc.service.LogService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsTyepeServceImpl implements GoodsTyepeServce {

    @Autowired
    private LogService logService;

    @Autowired
    private GoodsTypeDao goodsTypeDao;


    @Override
    public String loadGoodsType() {
        logService.save(new Log(Log.SELECT_ACTION,"查询商品类别信息"));
        return this.getAllGoodsTeypes(-1).toString();
    }

    /**
     *  查询所有的商品类别
     * @return
     */
    private JsonArray getAllGoodsTeypes(Integer parentId) {
        JsonArray array =  this.getGoodsTypeByParentId(parentId);

        for (int i = 0; i < array.size(); i++) {
           JsonObject obj = (JsonObject) array.get(i);
            if (obj.get("state").getAsString().equals("open")) {
                // 如果是叶子节点 ， 不用在递归
                continue;
            } else {
                // 如果是根节点 ，继续递归查询
                obj.add("children",this.getAllGoodsTeypes(obj.get("id").getAsInt()));
            }
        }
        return array;
    }

    /**
     * 根据父ID获取所有子商品类别
     * @param parentId
     * @return
     */
    private JsonArray getGoodsTypeByParentId(Integer parentId) {
        JsonArray array = new JsonArray();
        List<GoodsType> goodsTypeList = goodsTypeDao.getAllGoodsTypeByParentId(parentId);
        // 遍历商品类别

        for (GoodsType goodsType : goodsTypeList) {

            JsonObject obj = new JsonObject();
            obj.addProperty("id",goodsType.getGoodsTypeId());  // ID

            obj.addProperty("text",goodsType.getGoodsTypeName());
            if (goodsType.getGoodsTypeState() == 1){
                obj.addProperty("state","closed");  // 根节点

            } else {
                obj.addProperty("state","open");
            }
            obj.addProperty("iconCls","goods-type");
            JsonObject attributes = new JsonObject();
            //图标默认为自定义的商品类别图标
            attributes.addProperty("state",goodsType.getGoodsTypeState());   // 就加入当前节点的类型
            obj.add("attributes",attributes);
            array.add(obj);

        }
        return array;

    }
}
