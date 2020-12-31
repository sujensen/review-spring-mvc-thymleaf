package com.udacity.jwdnd.c1.review.config;

import com.udacity.jwdnd.c1.review.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/*
Using this class to add to Spring's Web Security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /*
    Configure Spring's authentication manager.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        // Tells Spring to use our authentication method to check log-ins.
        auth.authenticationProvider(this.authenticationService);
    }

    /*
    Used to configure the HttpSecurity object by chaining methods to express security requirements
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Users to /signup, /css, and /js do NOT have to log in.  Anything that doesn't
        // match those urls, must authenticate.
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/home", true);

        http.csrf().disable();
        http.logout().permitAll();
    }
}
