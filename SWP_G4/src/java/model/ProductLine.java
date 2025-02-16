/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class ProductLine {
    /*[productLineId] [int] IDENTITY(1,1) NOT NULL,
	[productLineName] [nvarchar](255) NULL,
	[categoryId] [int] NULL,
	[brandId] [int] NULL,*/
    private int id;
    private int productLineId;
    private String productLineName;
    private int categoryId;
    private int brandId;
    private int price;
    private String image;
    private int colorId;
    private String colorValue;
    private int sizeId;
    private int sizeValue;
    private int quantity;
    private String categoryName;
    private String brandName;
    private String description;
    private String title;
    private int total;
    private String statusValue;
    private Double reducedAmount;
private int totalPriceSold;
    private int totalQuantitySold;

    public ProductLine() {
    }
 public int getTotalPriceSold() {
        return totalPriceSold;
    }

    public void setTotalPriceSold(int totalPriceSold) {
        this.totalPriceSold = totalPriceSold;
    }

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(int totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }

    public int getTotal() {
        return total;
    }

    public Double getReducedAmount() {
        return reducedAmount;
    }

    public void setReducedAmount(Double reducedAmount) {
        this.reducedAmount = reducedAmount;
    }
    

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
    
 public ProductLine(int id, int productLineId, String productLineName, String brandName, int price, String image, String categoryName, int totalPriceSold, int totalQuantitySold) {
        this.id = id;
        this.productLineId = productLineId;
        this.productLineName = productLineName;
        this.brandName = brandName;
        this.price = price;
        this.image = image;
        this.categoryName = categoryName;
        this.totalPriceSold = totalPriceSold;
        this.totalQuantitySold = totalQuantitySold;
    }

    public ProductLine(int id,int productLineId, String productLineName, int categoryId, int brandId, int price, String image, String categoryName, String brandName, String description, String title) {
        this.id =id;
        this.productLineId = productLineId;
        this.productLineName = productLineName;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.price = price;
        this.image = image;
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.description = description;
        this.title =title;
    }

    public ProductLine(int price, String image, String colorValue, int sizeValue, int quantity, String title,String statusValue, int total, double reducedAmount) {
        this.price = price;
        this.image = image;
        this.colorValue = colorValue;
        this.sizeValue = sizeValue;
        this.quantity = quantity;
        this.title = title;
        this.statusValue = statusValue;
        this.total = total;
        this.reducedAmount = reducedAmount;
    }

    public ProductLine(int price, String image, String colorValue, int sizeValue, int quantity, String description, String title, int total, String statusValue) {
        this.price = price;
        this.image = image;
        this.colorValue = colorValue;
        this.sizeValue = sizeValue;
        this.quantity = quantity;
        this.description = description;
        this.title = title;
        this.total = total;
        this.statusValue = statusValue;
    }
    
    

    

    public ProductLine(int productLineId, String productLineName, int categoryId, int brandId, int price, String image) {
        this.productLineId = productLineId;
        this.productLineName = productLineName;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.price = price;
        this.image = image;
    }

    public ProductLine(int productLineId, String productLineName, int price, String image, String categoryName, String brandName, String description) {
        this.productLineId = productLineId;
        this.productLineName = productLineName;
        this.price = price;
        this.image = image;
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.description = description;
    }

    public ProductLine(int productLineId, String productLineName) {
        this.productLineId = productLineId;
        this.productLineName = productLineName;
    }

    public ProductLine(int productLineId, int colorId, String colorValue, int sizeId, int sizeValue, int quantity) {
        this.productLineId = productLineId;
        this.colorId = colorId;
        this.colorValue = colorValue;
        this.sizeId = sizeId;
        this.sizeValue = sizeValue;
        this.quantity = quantity;
    }

    public ProductLine(int productLineId, String productLineName, int categoryId, int brandId) {
        this.productLineId = productLineId;
        this.productLineName = productLineName;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public ProductLine(int productLineId, String productLineName, int categoryId, int brandId, String categoryName, String brandName) {
        this.productLineId = productLineId;
        this.productLineName = productLineName;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.categoryName = categoryName;
        this.brandName = brandName;
    }
    
    
    

    

    public ProductLine( int id,int productLineId, String productLineName, int price, String image, String title,String categoryName, String brandName) {
        this.id = id;
        this.productLineId = productLineId;
        this.productLineName = productLineName;
        this.price = price;
        this.image = image;
        this.title = title;
        this.categoryName = categoryName;
        this.brandName = brandName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
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

    public int getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(int sizeValue) {
        this.sizeValue = sizeValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    

 

   

    public int getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(int productLineId) {
        this.productLineId = productLineId;
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return colorValue;
    }

    public void setColor(String colorValue) {
        this.colorValue = colorValue;
    }

    public int getSize() {
        return sizeValue;
    }

    public void setSize(int sizeValue) {
        this.sizeValue = sizeValue;
    }

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductLine{" + "productLineId=" + productLineId + ", categoryId=" + categoryId + ", brandId=" + brandId + ", categoryName=" + categoryName + ", brandName=" + brandName + '}';
    }

   
    

   

   

}