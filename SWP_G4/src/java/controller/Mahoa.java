/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.security.MessageDigest;
import java.util.Base64;

/**
 *
 * @author dung2
 */
class Mahoa {

    public static String toSHA1(String str) {
        String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";
        String result = null;
        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1"); // Added '='
            result = Base64.getEncoder().encodeToString(md.digest(dataBytes)); // Changed Base64.encodeBase64String to Base64.getEncoder().encodeToString
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; // Moved return statement outside of the try-catch block
    }
    
    
}
