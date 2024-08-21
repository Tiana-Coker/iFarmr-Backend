package org.ifarmr.utils;

public class EmailUtil {

    public static String getVerificationUrl( String token){
        return  "http://localhost:8080/api/v1/auth/confirm?token=" + token ;
    }

}
