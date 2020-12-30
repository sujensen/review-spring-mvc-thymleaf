package com.udacity.jwdnd.c1.review.services;

import com.udacity.jwdnd.c1.review.data.User;
import com.udacity.jwdnd.c1.review.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/*
Uses the User and the UserMapper (very simple classes) to perform more complex tasks:
Validate that a user has logged in successfully.

AuthenticationProvider and the @Override annotation provide a hook for Spring Security.

When a user logs in, we have no way to retrieve their original password, but we can
re-hash their user input and see if it matches the hashed value in our database.
*/
@Service
public class AuthenticationService implements AuthenticationProvider {

    private UserMapper userMapper;
    private HashService hashService;

    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    // @Override-annotated authentication method is used by Spring Security to check the
    // credentials it received via its auth'n login form.
    // Our implementation of this method tells Spring HOW to check.
    // Only returns a token if the log-in was correct.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userMapper.getUser(username);
        if (user != null) {
            String encodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if (user.getPassword().equals(hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }

        return null;
    }

    // @Override-annotated supports method tells Spring which authentication methods it can handle.
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
