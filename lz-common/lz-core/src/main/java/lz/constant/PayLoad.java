package lz.constant;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author :Lictory
 * @date : 2024/01/25
 */

@Data
@Accessors(chain = true)

public class PayLoad {
    private Long userId;
}
