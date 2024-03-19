package com.lz.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author :Lictory
 * @date : 2024/03/10
 */
@Data
@Accessors(chain = true)
public class RegisterVO {
    String message;
    Boolean result;
}
