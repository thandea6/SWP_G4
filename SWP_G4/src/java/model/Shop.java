/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Shop {

    /*[shopId] [int] IDENTITY(1,1) NOT NULL,
	[shopName] [nvarchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[image] [varchar](255) NULL,
	[accountBalance] [money] NULL,
	[accountId] [int] NULL,*/
    private int shopId;
    private String shopName;
    private String address;
    private String image;
    private double accountBalance;
    private int accountId;
    private String phone;
    private String email;
    private int numberOfProductLineId;
    
    public Shop() {
    }

    public Shop(int shopId, int numberOfProductLineId) {
        this.shopId = shopId;
        this.numberOfProductLineId = numberOfProductLineId;
    }
    
    
    public Shop(int shopId, String shopName, String image) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.image = image;
    }

    public Shop(int shopId,String shopName, String address, String image, int accountId, String phone, String email) {
        this.shopId =shopId;
        this.shopName = shopName;
        this.address = address;
        this.image = image;
        this.accountId = accountId;
        this.phone = phone;
        this.email = email;
    }

    public Shop(int shopId, String shopName, String address, String image, double accountBalance, int accountId) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.address = address;
        this.image = image;
        this.accountBalance = accountBalance;
        this.accountId = accountId;
    }

    public Shop(int shopId, String shopName, String address, String image, double accountBalance, int accountId, String phone, String email) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.address = address;
        this.image = image;
        this.accountBalance = accountBalance;
        this.accountId = accountId;
        this.phone = phone;
        this.email = email;
    }

    public int getNumberOfProductLineId() {
        return numberOfProductLineId;
    }

    public void setNumberOfProductLineId(int numberOfProductLineId) {
        this.numberOfProductLineId = numberOfProductLineId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Shop{" + "shopId=" + shopId + ", shopName=" + shopName + ", address=" + address + ", image=" + image + ", accountBalance=" + accountBalance + ", accountId=" + accountId + ", phone=" + phone + ", email=" + email + ", NumberOfProductLineId=" + numberOfProductLineId + '}';
    }

    

}
