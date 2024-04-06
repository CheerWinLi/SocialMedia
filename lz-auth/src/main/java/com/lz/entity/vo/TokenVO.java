package com.lz.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author :Lictory
 * @date : 2024/04/06
 */
@Data
@Accessors(chain = true)
public class TokenVO {
    String username;
    String token;
    Long id;
}

