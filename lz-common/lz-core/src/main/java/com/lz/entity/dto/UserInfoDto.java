package com.lz.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author :Lictory
 * @date : 2024/05/08
 */

@Data
@Accessors(chain = true)
public class UserInfoDto {
    private Integer id;
    private String username;
}
