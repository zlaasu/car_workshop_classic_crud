package org.zlasu.controler;

public class Login {
    public static boolean checkTokenForUser(String token) {
        if (token.equals("janek")) {
            return true;
        }
        return false;
    }
}
