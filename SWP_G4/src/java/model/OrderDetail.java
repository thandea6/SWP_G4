/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


public class OrderDetail {

    private int orderDetailId;
    private int quantity;
    private String statusValue;
    private String colorValue;
    private String sizeValue;
    private String title;
    private Date orderDate;
    private double price;
    private int shopId;
    private String shopName;
    private String image;
    private int statusId;
    private int shopProductId;
    private int productItemId;
    private int orderId;
    private String starRating;
    private String code;
    private String content;
    private double totalmoney;
    private double reducedAmount;

    public double getReducedAmount() {
        return reducedAmount;
    }

    public void setReducedAmount(double reducedAmount) {
        this.reducedAmount = reducedAmount;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, int quantity, String statusValue, String colorValue, String sizeValue, String title, Date orderDate, double price, int shopId, String shopName, String image) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.statusValue = statusValue;
        this.colorValue = colorValue;
        this.sizeValue = sizeValue;
        this.title = title;
        this.orderDate = orderDate;
        this.price = price;
        this.shopId = shopId;
        this.shopName = shopName;
        this.image = image;
    }

    public OrderDetail(int orderDetailId, int quantity, String statusValue, String colorValue, String sizeValue, String title, Date orderDate, double price, int shopId, String shopName, String image, String starRating) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.statusValue = statusValue;
        this.colorValue = colorValue;
        this.sizeValue = sizeValue;
        this.title = title;
        this.orderDate = orderDate;
        this.price = price;
        this.shopId = shopId;
        this.shopName = shopName;
        this.image = image;
        this.starRating = starRating;
    }


    public String getStarRating() {
        return starRating;
    }
    public OrderDetail(int orderDetaiId, int quantity, String colorValue, String sizeValue, String title, Date orderDate, double price, int shopId, String shopName, String image, double total, int ProductItemId) {
        this.orderDetailId = orderDetaiId;
        this.quantity = quantity;
        this.colorValue = colorValue;
        this.sizeValue = sizeValue;
        this.title = title;
        this.orderDate = orderDate;
        this.price = price;
        this.shopId = shopId;
        this.shopName = shopName;
        this.image = image;
        this.totalmoney = total;
        this.productItemId = ProductItemId;
    }

    public String getContent() {
        return content;

    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

   
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(int productItemId) {
        this.productItemId = productItemId;
    }

    public int getShopProductId() {
        return shopProductId;
    }

    public void setShopProductId(int shopProductId) {
        this.shopProductId = shopProductId;
    }

    public OrderDetail(int orderDetailId, int quantity, String statusValue, String colorValue, String sizeValue, String title, Date orderDate, double price) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.statusValue = statusValue;
        this.colorValue = colorValue;
        this.sizeValue = sizeValue;
        this.title = title;
        this.orderDate = orderDate;
        this.price = price;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailId=" + orderDetailId + ", quantity=" + quantity + ", statusValue=" + statusValue + ", colorValue=" + colorValue + ", sizeValue=" + sizeValue + ", title=" + title + ", orderDate=" + orderDate + ", price=" + price + ", shopId=" + shopId + ", shopName=" + shopName + ", image=" + image + ", statusId=" + statusId + ", shopProductId=" + shopProductId + ", productItemId=" + productItemId + ", orderId=" + orderId + ", starRating=" + starRating + '}';
    }

    

    
}
