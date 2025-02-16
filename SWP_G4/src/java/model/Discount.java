package model;


import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Discount {
    //    	[discountId] [int] IDENTITY(1,1) NOT NULL,
//	[shopProductId] [int] NULL,
//	[discountValue] [int] NULL,
//	[startDate] [datetime] NULL,
//	[endDate] [datetime] NULL,
//	[promotionalPrice] [money] NULL,
    private int discountId;
    private int shopProductId;
    private int discountValue;
    private Date startDate;
    private Date endDate;
    private double promotionalPrice;

    public Discount() {
    }

    public Discount(int discountId, int shopProductId, int discountValue, Date startDate, Date endDate, double promotionalPrice) {
        this.discountId = discountId;
        this.shopProductId = shopProductId;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionalPrice = promotionalPrice;
    }

    public Discount(int shopProductId, int discountValue, double promotionalPrice) {
        this.shopProductId = shopProductId;
        this.discountValue = discountValue;
        this.promotionalPrice = promotionalPrice;
    }
    

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getShopProductId() {
        return shopProductId;
    }

    public void setShopProductId(int shopProductId) {
        this.shopProductId = shopProductId;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(double promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

    @Override
    public String toString() {
        return "Discount{" + "discountId=" + discountId + ", shopProductId=" + shopProductId + ", discountValue=" + discountValue + ", startDate=" + startDate + ", endDate=" + endDate + ", promotionalPrice=" + promotionalPrice + '}';
    }
    
    

}
