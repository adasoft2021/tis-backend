package com.adasoft.tis.core.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = JWTProvider.class)
class JWTProviderTest {
    @Autowired
    private JWTProvider jwtProvider;

    private static final Long USER_ID = 860104577966742482L;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtProvider.create(USER_ID);
    }

    @Test
    void createToken() {
        assertNotEquals(token, null);
    }

    @Test
    void tokenNotEmpty() {
        assertNotEquals(token, "");
    }

    @Test
    void getUserId() {
        Long decryptedUserId = jwtProvider.decryptUserId(token);
        assertEquals(decryptedUserId, USER_ID);
    }
}