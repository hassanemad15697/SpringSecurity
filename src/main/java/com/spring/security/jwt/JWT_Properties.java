package com.spring.security.jwt;

public class JWT_Properties {
    public static final String SECRET = "HASSAN_KEY";
    public static final int EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
