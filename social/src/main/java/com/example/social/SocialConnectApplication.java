package com.example.social;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SocialConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialConnectApplication.class, args);
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal CryptoAuthenticatedPrincipal principal,
                                    OAuth2AuthenticationToken token) {
        String providerId = token.getAuthorizedClientRegistrationId();
        return new HashMap<String, Object>() {{
            put("provider", providerId);
            put("name", principal.getUserName());
        }};
    }

}
