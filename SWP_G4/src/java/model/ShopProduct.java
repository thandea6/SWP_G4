/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


public class ShopProduct {

    /*[id] [int] IDENTITY(1,1) NOT NULL,
	[shopId] [int] NULL,
	[productLine] [int] NULL,
	[price] [money] NULL,
	[description] [nvarchar](255) NULL,
	[created_at] [date] NULL,
	[updated_at] [date] NULL,
	[is_deleted] [bit] NULL,*/
    private int id;
    private int shopId;
    private ProductLine productLineId;
    private double price;
    private String description;
    private Date created_at;
    private Date updated_at;
    private boolean is_deleted;
    private String title;
    private int averageRating;
    private int totalSold;
    private int discountValue;
    private double discountPrice;
    private Shop shop;
    private String image;

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    @Override
    public String toString() {
        return "ShopProduct{" + "id=" + id + ", shopId=" + shopId + ", productLineId=" + productLineId + ", price=" + price + ", description=" + description + ", created_at=" + created_at + ", updated_at=" + updated_at + ", is_deleted=" + is_deleted + ", title=" + title + ", averageRating=" + averageRating + ", shop=" + shop + ", image=" + image + '}';
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public ShopProduct() {
    }

    public ShopProduct(int id, double price, String image) {
        this.id = id;
        this.price = price;
        this.image = image;
    }

    public ShopProduct(int id, double price, String image, String title) {
        this.id = id;
        this.price = price;
        this.image = image;
        this.title = title;
    }

    public ShopProduct(int id, int shopId, double price, String description, String title, String image) {
        this.id = id;
        this.shopId = shopId;

        this.price = price;
        this.description = description;
        this.title = title;
        this.image = image;
    }

    public ShopProduct(int id, int shopId, ProductLine productLineId, double price, String description, Date created_at, Date updated_at, boolean is_deleted, Shop shop, String image, String title) {
        this.id = id;
        this.shopId = shopId;
        this.productLineId = productLineId;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_deleted = is_deleted;
        this.shop = shop;
        this.image = image;
        this.title = title;
    }

    public ShopProduct(int id, int shopId, ProductLine productLineId, double price, String description, Date created_at, Date updated_at, boolean is_deleted, Shop shop, String image, String title, int discountValue) {

        this.id = id;
        this.shopId = shopId;
        this.productLineId = productLineId;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_deleted = is_deleted;
        this.shop = shop;
        this.image = image;
        this.title = title;
        this.discountValue = discountValue;
    }

    public ShopProduct(int id, int shopId, ProductLine productLineId, double price, String description, Date created_at, Date updated_at, boolean is_deleted, String title, int averageRating, Shop shop, String image, int totalSold, int discountValue, double discountPrice) {
        this.id = id;
        this.shopId = shopId;
        this.productLineId = productLineId;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_deleted = is_deleted;
        this.title = title;
        this.averageRating = averageRating;
        this.shop = shop;
        this.image = image;
        this.totalSold = totalSold;
        this.discountValue = discountValue;
        this.discountPrice = discountPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ShopProduct(int id, int shopId, ProductLine productLineId, double price, String description, Date created_at, Date updated_at, boolean is_deleted) {
        this.id = id;
        this.shopId = shopId;
        this.productLineId = productLineId;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_deleted = is_deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getImage() {
        return image;
    }

    public void setImages(String image) {
        this.image = image;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public ProductLine getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(ProductLine productLineId) {
        this.productLineId = productLineId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

}
