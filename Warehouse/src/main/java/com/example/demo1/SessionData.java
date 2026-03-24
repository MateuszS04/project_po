package com.example.demo1;

public class SessionData {
    public static String currentLogin;
    public static boolean currentAdmin;
    public static void setAdmin(boolean admin) {
        currentAdmin = admin;
    }
    public static boolean getAdmin() {
        return currentAdmin;
    }
    public static void setCurrentLogin(String login) {
        currentLogin = login;
    }

    public static String getCurrentLogin() {
        return currentLogin;
    }
}