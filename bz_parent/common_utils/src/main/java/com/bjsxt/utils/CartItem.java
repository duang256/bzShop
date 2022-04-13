package com.bjsxt.utils;

/**
 * 购物车商品模型
 */
public class CartItem {
    private Long id;
    private String title;
    private String sellPoint;
    private String image;
    private int num;
    private Long price;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public String getImage() {
        return image;
    }

    public int getNum() {
        return num;
    }

    public Long getPrice() {
        return price;
    }
}
