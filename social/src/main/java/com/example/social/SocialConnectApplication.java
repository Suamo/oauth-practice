package com.example.social;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SocialConnectApplication {
    private static final Map<String, String> PROVIDERS_USERNAME_ATTRIBUTES = new HashMap<String, String>() {{
        put("github", "login");
        put("facebook", "name");
    }};

    public static void main(String[] args) {
        SpringApplication.run(SocialConnectApplication.class, args);
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal,
                                    OAuth2AuthenticationToken token) {
        String providerId = token.getAuthorizedClientRegistrationId();
        String userNameAttribute = PROVIDERS_USERNAME_ATTRIBUTES.get(providerId);
        if (userNameAttribute == null) {
            throw new IllegalArgumentException("Please configure username attribute for the provider");
        }

        return Collections.singletonMap("name", principal.getAttribute(userNameAttribute));
    }

}
