/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


public class Voucher {
    /*[voucherId] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](20) NULL,
	[reducedAmount] [money] NULL,
	[endDate] [datetime] NULL,
	[isGlobal] [bit] NULL,
	[description] [nvarchar](255) NULL,*/
    private int voucherId;
    private String code;
    private double reducedAmount;
    private Date endDate;
    private String description;
    private boolean isGlobal;
    private int quantity;
    private int shopId;
    private String shopName;

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
    
    
    public Voucher() {
    }

    public Voucher(int voucherId, String code, double reducedAmount, Date endDate, String description, boolean isGlobal) {
        this.voucherId = voucherId;
        this.code = code;
        this.reducedAmount = reducedAmount;
        this.endDate = endDate;
        this.description = description;
        this.isGlobal = isGlobal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getReducedAmount() {
        return reducedAmount;
    }

    public void setReducedAmount(double reducedAmount) {
        this.reducedAmount = reducedAmount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    @Override
    public String toString() {
        return "Voucher{" + "voucherId=" + voucherId + ", code=" + code + ", reducedAmount=" + reducedAmount + ", endDate=" + endDate + ", description=" + description + ", isGlobal=" + isGlobal + '}';
    }
    
}
