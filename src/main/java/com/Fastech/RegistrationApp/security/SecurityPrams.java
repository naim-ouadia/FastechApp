package com.Fastech.RegistrationApp.security;

public interface SecurityPrams {

    public static final String JWT_HEADER_NAME = "Authorization";
    public static final String SECRET = "soyas";
    public static final long EXPEIRATION = 10 * 24 * 3600*1000;
    public static final String HEADER_PREFIX = "Bearer ";

}
