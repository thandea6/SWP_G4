package model;

import java.time.LocalTime;

public class Message {
    private int messageId;
    private int chatId;
    private LocalTime senderTime; // Changed from Date to LocalTime
    private int senderId;
    private String content;
    private Shop shop;
    private User user;

    public Message() {
    }

    public Message(int messageId, int chatId, LocalTime senderTime, int senderId, String content, Shop shop, User user) {
        this.messageId = messageId;
        this.chatId = chatId;
        this.senderTime = senderTime;
        this.senderId = senderId;
        this.content = content;
        this.shop = shop;
        this.user = user;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public LocalTime getSenderTime() {
        return senderTime;
    }

    public void setSenderTime(LocalTime senderTime) {
        this.senderTime = senderTime;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Message{" + "messageId=" + messageId + ", chatId=" + chatId + ", senderTime=" + senderTime + ", senderId=" + senderId + ", content=" + content + ", shop=" + shop + ", user=" + user + '}';
    }
}
