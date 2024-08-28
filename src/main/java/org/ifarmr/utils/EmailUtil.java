package org.ifarmr.utils;

import org.ifarmr.config.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    private final UrlConfig urlConfig;

    @Autowired
    public EmailUtil(UrlConfig urlConfig) {
        this.urlConfig = urlConfig;
    }


    public static String getVerificationUrl( String token){
        return  "http://localhost:8080/api/v1/auth/confirm?token=" + token ;
    }
    public String getResetPasswordUrl(String token) {
        return urlConfig.getResetPasswordUrl() + token;
    }
}


