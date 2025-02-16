/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Rating {

    private int ratingId;
    private int userId;
    private int shopProductId;
    private Date created_at;
    private Date updated_at;
    private int starRating;
    private int average_starRating;
    private User user;
    private String content;
    private String colorValue;
    private String sizeValue;
     private int num_Rating;

    public Rating() {
    }

    public Rating(int average_starRating) {
        this.average_starRating = average_starRating;
    }

    public Rating(int ratingId, int userId, int shopProductId, Date created_at, Date updated_at, int starRating, int average_starRating, User user, String content, String colorValue, String sizeValue) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.shopProductId = shopProductId;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.starRating = starRating;
        this.average_starRating = average_starRating;
        this.user = user;
        this.content = content;
        this.colorValue = colorValue;
        this.sizeValue = sizeValue;
    }

    
    
    
    public Rating(int ratingId, int userId, int shopProductId, Date created_at, Date updated_at, int starRating) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.shopProductId = shopProductId;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.starRating = starRating;
    }

    public Rating(int ratingId, int num_Rating) {
        this.ratingId = ratingId;
        this.num_Rating = num_Rating;
    }

    public int getNum_Rating() {
        return num_Rating;
    }

    public void setNum_Rating(int num_Rating) {
        this.num_Rating = num_Rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public int getAverage_starRating() {
        return average_starRating;
    }

    public void setAverage_starRating(int average_starRating) {
        this.average_starRating = average_starRating;
    }

    @Override
    public String toString() {
        return "Rating{" + "ratingId=" + ratingId + ", userId=" + userId + ", shopProductId=" + shopProductId + ", created_at=" + created_at + ", updated_at=" + updated_at + ", starRating=" + starRating + ", average_starRating=" + average_starRating + ", user=" + user + ", content=" + content + ", colorValue=" + colorValue + ", sizeValue=" + sizeValue + '}';
    }

    
    

   

    

}
