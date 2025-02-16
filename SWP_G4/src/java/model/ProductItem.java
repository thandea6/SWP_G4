/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class ProductItem {

    /*[productItemId] [int] IDENTITY(1,1) NOT NULL,
	[shopProductId] [int] NULL,
	[sizeId] [int] NULL,
	[colorId] [int] NULL,
	[quantity] [int] NULL,*/
    private int productItemId;
    private int shopProductId;
    private int sizeId;
    private int colorId;
    private int quantity;
    private int sizeValue;
    private String colorValue;
    private ShopProduct shopProduct;
    private int sumQuantity;
    private Shop shop;

    public int getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(int sumQuantity) {
        this.sumQuantity = sumQuantity;
    }
    public ProductItem() {
    }

    public ShopProduct getShopProduct() {
        return shopProduct;
    }

    public void setShopProduct(ShopProduct shopProduct) {
        this.shopProduct = shopProduct;
    }

    public int getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(int sizeValue) {
        this.sizeValue = sizeValue;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    public ProductItem(int shopProductId, int sizeId, int colorId, int quantity) {
        this.shopProductId = shopProductId;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.quantity = quantity;
    }

    public ProductItem(int productItemId, int sumQuantity) {
        this.productItemId = productItemId;
        this.sumQuantity = sumQuantity;
    }

    public ProductItem(int productItemId, int shopProductId, int sizeId, int colorId, int quantity, int sizeValue, String colorValue, ShopProduct shopProduct, Shop shop) {
        this.productItemId = productItemId;
        this.shopProductId = shopProductId;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.quantity = quantity;
        this.sizeValue = sizeValue;
        this.colorValue = colorValue;
        this.shopProduct = shopProduct;
        this.shop = shop;
    }
    
        public ProductItem(int productItemId, int shopProductId, int sizeId, int colorId, int quantity, int sizeValue, String colorValue, ShopProduct shopProduct) {
        this.productItemId = productItemId;
        this.shopProductId = shopProductId;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.quantity = quantity;
        this.sizeValue = sizeValue;
        this.colorValue = colorValue;
        this.shopProduct = shopProduct;
    }

    public ProductItem(int productItemId, int shopProductId, int sizeId, int colorId, int quantity) {
        this.productItemId = productItemId;
        this.shopProductId = shopProductId;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.quantity = quantity;
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

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "ProductItem{" + "productItemId=" + productItemId + ", shopProductId=" + shopProductId + ", sizeId=" + sizeId + ", colorId=" + colorId + ", quantity=" + quantity + ", sizeValue=" + sizeValue + ", colorValue=" + colorValue + ", shopProduct=" + shopProduct + ", sumQuantity=" + sumQuantity + '}';
    }

}
