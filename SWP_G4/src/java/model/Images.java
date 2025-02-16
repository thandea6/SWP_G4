/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Images {
    /*[imagesId] [int] IDENTITY(1,1) NOT NULL,
	[shopProductId] [int] NULL,
	[imageLink] [varchar](255) NULL,*/
    private int imagesId;
    private int shopProductId;
    private String imageLink;

    @Override
    public String toString() {
        return "Images{" + "imagesId=" + imagesId + ", shopProductId=" + shopProductId + ", imageLink=" + imageLink + '}';
    }

    public Images() {
    }

    public Images(int imagesId, int shopProductId, String imageLink) {
        this.imagesId = imagesId;
        this.shopProductId = shopProductId;
        this.imageLink = imageLink;
    }

    public int getImagesId() {
        return imagesId;
    }

    public void setImagesId(int imagesId) {
        this.imagesId = imagesId;
    }

    public int getShopProductId() {
        return shopProductId;
    }

    public void setShopProductId(int shopProductId) {
        this.shopProductId = shopProductId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    
}
