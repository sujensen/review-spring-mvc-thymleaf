package com.udacity.jwdnd.c1.review.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/*
The hashing service itself has a single method that takes some data and salt and
creates a string representing the hashed value.

Salt: random data that is combined with the input string when hashing so that the
resultant hashed values are unique for each row. This means that two users with the
same password would not have the same hash in the database.
 */
@Component
public class HashService {
    private Logger logger = LoggerFactory.getLogger(HashService.class);

    public String getHashedValue(String data, String salt) {
        byte[] hashedValue = null;

        KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), 5000, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hashedValue = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }

        return Base64.getEncoder().encodeToString(hashedValue);
    }
}
