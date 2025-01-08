package com.mahakim.app.constant;

public class SecurityConstant {
	public static final long EXPIRATION_TIME_TOKEN = 10_800_000; 
	public static final long EXPIRATION_TIME_REFRESHTOKEN = 432_000_000;// 5 days expressed in milliseconds
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
	public static final String BOOK_LIBRARY_LLC = "Support Center Api, LLC";
	public static final String BOOK_LIBRARY_ADMINISTRATION = "Support Center Api";
	public static final String AUTHORITIES = "authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
	public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = { "/user/login", "/user/register", "/user/image/**","/user/refreshtoken" };
//	public static final String[] PUBLIC_URLS = { "**" };
}
