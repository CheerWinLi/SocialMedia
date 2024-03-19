package com.lz.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */

@Data
public class LoginBO {

    @NotBlank(message="用户名不能为空")
    private String username;

    @NotBlank(message = "请输入密码")
    private String password;
}
