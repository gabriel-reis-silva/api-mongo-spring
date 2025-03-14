package br.com.gabrielreis.pipo.constants;

public class SecurityConstants {

    public static final String PRIVATE_URL = "/api/private";
    public static final String AUTH_LOGIN_URL = "/api/authenticate";

    public static final String JWT_SECRET = "2r5u8x/A?D*G-KaPdSgVkYp3s6v9y$B&E)H@MbQeThWmZq4t7w!z%C*F-JaNdRfU";

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
