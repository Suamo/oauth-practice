package com.example.social;

import com.example.social.usertype.FacebookConnectUser;
import com.example.social.usertype.GithubConnectUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        HttpSecurity security = http
                .oauth2Login()
                .userInfoEndpoint()
                .customUserType(FacebookConnectUser.class, "facebook")
                .customUserType(GithubConnectUser.class, "github")
                .and()
                .and();

        security
                .authorizeRequests()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/**").access("isFullyAuthenticated()");
    }

}
