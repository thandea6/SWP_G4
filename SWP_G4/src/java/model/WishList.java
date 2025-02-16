/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

public class WishList {

    private int id;
    private int userId;
    private int shopProductId;
    private Date date;

    @Override
    public String toString() {
        return "WishList{" + "id=" + id + ", userId=" + userId + ", shopProductId=" + shopProductId + ", date=" + date + '}';
    }

    public WishList() {
    }

    public WishList(int id, int userId, int shopProductId, Date date) {
        this.id = id;
        this.userId = userId;
        this.shopProductId = shopProductId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopProductId() {
        return shopProductId;
    }

    public void setShopProductId(int shopProductId) {
        this.shopProductId = shopProductId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
