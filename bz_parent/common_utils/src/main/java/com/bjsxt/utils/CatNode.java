package com.bjsxt.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * portal分类菜单节点类
 * 没有子节点直接加入List,有子节点的使用该类表示
 */
public class CatNode implements Serializable {

    /**
     * 此处表示转换为json对象时使用"n"这个字段,前端需要的是"n"
     */
    @JsonProperty("n")
    private String name;
    @JsonProperty("i")
    private List<?> item;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
