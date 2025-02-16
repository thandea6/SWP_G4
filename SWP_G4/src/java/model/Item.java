/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Item {

    private ProductItem productItem;
    private int sizeId;
    private String colorValue;
    private int quantity;
    private double price;
    private String shopName;
    private int status;
    private String code;
    private double promotionalPrice;

    
    
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setPromotionalPrice(double promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

    public double getPromotionalPrice() {
        return promotionalPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Item(ProductItem productItem, int sizeId, String colorValue, int quantity, double price, int status) {
        this.productItem = productItem;
        this.sizeId = sizeId;
        this.colorValue = colorValue;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public Item() {
    }

    public Item(ProductItem productItem, int quantity, double price) {
        this.productItem = productItem;
        this.quantity = quantity;
        this.price = price;
    }

    public Item(ProductItem productItem, String shopName, int sizeId, String colorValue, int quantity, double price, String code, double PromotionalPrice) {
        this.productItem = productItem;
        this.shopName = shopName;
        this.sizeId = sizeId;
        this.colorValue = colorValue;
        this.quantity = quantity;
        this.price = price;
        this.code = code;
        this.promotionalPrice = PromotionalPrice;
    }

    public String getShop() {
        return shopName;
    }

    public void setShop(String shop) {
        this.shopName = shop;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "productItem=" + productItem + ", sizeId=" + sizeId + ", colorValue=" + colorValue + ", quantity=" + quantity + ", price=" + price + ", shopName=" + shopName + ", status=" + status + ", code=" + code + ", promotionalPrice=" + promotionalPrice + '}';
    }

}
