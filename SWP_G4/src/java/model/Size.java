/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Size {
    /*[sizeId] [int] IDENTITY(1,1) NOT NULL,
	[sizeValue] [int] NULL,*/
    private int sizeId;
    private int sizeValue;

    public Size() {
    }

    public Size(int sizeId, int sizeValue) {
        this.sizeId = sizeId;
        this.sizeValue = sizeValue;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(int sizeValue) {
        this.sizeValue = sizeValue;
    }
    
}
