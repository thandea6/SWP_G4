/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

public class Comment {

    private int commentId;
    private int accountId;
    private int shopProductId;
    private Date created_at;
    private Date updated_at;
    private String content;
    private Account account;
    private User user;
    private int starRating;
    private String colorValue;
    private String sizeValue;
    private int num_contents;
    
    public Comment() {
    }

    public Comment(int num_contents) {
        this.num_contents = num_contents;
    }
    
    public Comment(int commentId, int accountId, int shopProductId, Date created_at, Date updated_at, String content, Account account, User user, int starRating, String colorValue, String sizeValue) {
        this.commentId = commentId;
        this.accountId = accountId;
        this.shopProductId = shopProductId;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.content = content;
        this.account = account;
        this.user = user;
        this.starRating = starRating;
        this.colorValue = colorValue;
        this.sizeValue = sizeValue;
    }

    

    public Comment(int commentId, int accountId, int shopProductId, Date created_at, Date updated_at, String content) {
        this.commentId = commentId;
        this.accountId = accountId;
        this.shopProductId = shopProductId;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.content = content;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getShopProductId() {
        return shopProductId;
    }

    public void setShopProductId(int shopProductId) {
        this.shopProductId = shopProductId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue;
    }

    public int getNum_contents() {
        return num_contents;
    }

    public void setNum_contents(int num_contents) {
        this.num_contents = num_contents;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", accountId=" + accountId + ", shopProductId=" + shopProductId + ", created_at=" + created_at + ", updated_at=" + updated_at + ", content=" + content + ", account=" + account + ", user=" + user + ", starRating=" + starRating + ", colorValue=" + colorValue + ", sizeValue=" + sizeValue + ", num_contents=" + num_contents + '}';
    }
    
    

    

}
