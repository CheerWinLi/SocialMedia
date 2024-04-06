package com.lz.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author :Lictory
 * @date : 2024/03/07
 */

@Data
public class RegisterBO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    private String email;

}
