/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class ChatBox {
    private int chatId;
    private int userId;
    private int shopId;
    private Shop shop;
    private User user;
    
    
    public ChatBox() {
    }

    public ChatBox(int chatId, int userId, int shopId, User user) {
        this.chatId = chatId;
        this.userId = userId;
        this.shopId = shopId;
        this.user = user;
    }
    
    public ChatBox(int chatId, int userId, int shopId, Shop shop, User user) {
        this.chatId = chatId;
        this.userId = userId;
        this.shopId = shopId;
        this.shop = shop;
        this.user = user;
    }
    
    

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ChatBox{" + "chatId=" + chatId + ", userId=" + userId + ", shopId=" + shopId + ", shop=" + shop + ", user=" + user + '}';
    }
    
    
}
