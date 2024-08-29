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
    
    public String getVerificationUrl(String token) {
        return urlConfig.getVerificationUrl() + token;
    }

    public String getResetPasswordUrl(String token) {
        return urlConfig.getResetPasswordUrl() + token;
    }
}


