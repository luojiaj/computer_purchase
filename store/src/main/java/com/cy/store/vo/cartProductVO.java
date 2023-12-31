package com.cy.store.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * 表示购物车数据的VO类，用来显示购物车列表
 */
public class cartProductVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;

    @Override
    public String toString() {
        return "cartProductVO{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", realPrice=" + realPrice +
                '}';
    }

    private Long realPrice;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        cartProductVO that = (cartProductVO) o;
        return Objects.equals(cid, that.cid) && Objects.equals(uid, that.uid) && Objects.equals(pid, that.pid) && Objects.equals(price, that.price) && Objects.equals(num, that.num) && Objects.equals(title, that.title) && Objects.equals(image, that.image) && Objects.equals(realPrice, that.realPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, uid, pid, price, num, title, image, realPrice);
    }
}
