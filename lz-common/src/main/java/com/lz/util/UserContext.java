package com.lz.util;

/**
 * @author :Lictory
 * @date : 2024/03/05
 */
public class UserContext {

    private static final ThreadLocal<Long> tl = new ThreadLocal<>();

    public static void setUser(Long userId) {
        tl.set(userId);
    }

    public static Long getUser() {
        return tl.get();
    }

    public static void removeUser() {
        tl.remove();
    }

}
