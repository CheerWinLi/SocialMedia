package lz.com.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author :Lictory
 * @date : 2024/04/02
 */

@Data
@Accessors(chain = true)
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
