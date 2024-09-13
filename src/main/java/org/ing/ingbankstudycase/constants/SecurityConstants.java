package org.ing.ingbankstudycase.constants;

public class SecurityConstants {

    public static final String SIGNING_KEY = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";
    public static final String AUTHORITIES_KEY = "scopes";
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGN_UP_URL = "/users";
    public static final String H2_CONSOLE = "/h2-console";


}
