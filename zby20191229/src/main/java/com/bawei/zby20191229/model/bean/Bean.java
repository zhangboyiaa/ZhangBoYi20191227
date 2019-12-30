package com.bawei.zby20191229.model.bean;

/**
 * date:2019/12/29
 * author:张博一(zhangboyi)
 * function:
 */
public class Bean {

    String name;
    String price;

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Bean(String name, String price) {
        this.name = name;
        this.price = price;
    }
}
