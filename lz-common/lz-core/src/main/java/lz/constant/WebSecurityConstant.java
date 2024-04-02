package lz.constant;


/**
 * @author :Lictory
 * @date : 2024/01/17
 */
public class WebSecurityConstant {
    /**
     * token
     */
    public static final String TOKEN_HEADER = "token";
    /**
     * 到期时间(ms) 30min
     */
    public static final Integer EXPIRE_TIME = 30 * 60 * 1000;
    public static final Long EXTEND_TIME = 2 * 60 * 60 * 1000L;
    public static final String ISSUER = "TopView-BlockChain";
    public static final String SIGN_KEY = "TopView-BlockChain";
    public static final String LOGIN_METHOD_NAME = "login";


}
