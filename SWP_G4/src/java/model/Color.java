/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Color {
    /*[colorId] [int] IDENTITY(1,1) NOT NULL,
	[colorValue] [varchar](255) NULL,*/
    private int colorId;
    private String colorValue;

    public Color() {
    }

    public Color(int colorId, String colorValue) {
        this.colorId = colorId;
        this.colorValue = colorValue;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }
    
}
