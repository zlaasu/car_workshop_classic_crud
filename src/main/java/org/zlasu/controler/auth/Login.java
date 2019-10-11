package org.zlasu.controler.auth;

public class Login {
    public static boolean checkTokenForUser(String token) {
        if (token !=null && token.equals("janek")) {
            return true;
        }
        return false;
    }
}
