package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author heaty566
 */
public class Constants {
public static final String GOOGLE_CLIENT_ID = System.getenv("GOOGLE_CLIENT_ID");
public static final String GOOGLE_CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");


	public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/SWP/LoginGoogleHandler";
        
        public static String GOOGLE_REDIRECT_URI_2 = "http://localhost:9999/SWP/LoginGoogleHandler";

	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

	public static String GOOGLE_GRANT_TYPE = "authorization_code";

}