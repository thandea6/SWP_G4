/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Order {
//    	[orderId] [int] IDENTITY(1,1) NOT NULL,
//	[userId] [int] NULL,
//	[orderDate] [date] NULL,
//	[totalMoney] [money] NULL,
//	[name] [nvarchar](255) NULL,
//	[address] [nvarchar](255) NULL,
//	[phone] [nvarchar](20) NULL,
//    
    
    private int orderId;
    private int userId;
    private Date date;
    private double totalMoney;
    private String name;
    private String address;
    private String phone;
    private int statusId;
    private String statusValue;
    private int year;
    private int month;
    private int day;
    private int totalQuantitySold;

    public Order() {
    }

    public Order(int month, int day, int totalQuantitySold) {
        this.month = month;
        this.day = day;
        this.totalQuantitySold = totalQuantitySold;
    }

    public Order( int month, int totalQuantitySold) {
        
        this.month = month;
        this.totalQuantitySold = totalQuantitySold;
    }
    
    public Order(int orderId, int userId, Date date, double totalMoney, String name, String address, String phone, int statusId, String statusValue) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.totalMoney = totalMoney;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.statusId =statusId;
        this.statusValue =statusValue;
    }

    public Order(int orderId, int userId, Date date, double totalMoney, String name, String address, String phone) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.totalMoney = totalMoney;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(int totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", userId=" + userId + ", date=" + date + ", totalMoney=" + totalMoney + ", name=" + name + ", address=" + address + ", phone=" + phone + ", statusId=" + statusId + ", statusValue=" + statusValue + ", year=" + year + ", month=" + month + ", day=" + day + ", totalQuantitySold=" + totalQuantitySold + '}';
    }
    
    

    
  
    
    
    
    
}
