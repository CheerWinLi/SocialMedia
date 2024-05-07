package com.lz.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author :Lictory
 * @date : 2024/04/29
 */
@Data
@Accessors(chain = true)
public class Message implements Serializable {
    private String toUserName;
    private String message;
}
