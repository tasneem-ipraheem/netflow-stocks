package com.netflow.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${netflow.users.admin.name}")
    private String adminUser;

    @Value("${netflow.users.admin.password}")
    private String adminPassword;

    @Value("${netflow.stokcs.security.enable}")
    private boolean securityEnabled;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (securityEnabled) {

            http
                    .authorizeRequests()
                        .antMatchers("/stocks/stats", "/manage/*").authenticated()
                        .anyRequest().permitAll()
                    .and()
                        .formLogin()
                        .permitAll()
                    .and()
                        .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .permitAll();
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        if (securityEnabled) {
            auth
                    .inMemoryAuthentication()
                    .withUser(adminUser).password(adminPassword).roles("ADMIN");
        }
    }
}