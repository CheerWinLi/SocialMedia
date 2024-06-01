package com.lz.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author :Lictory
 * @date : 2024/04/29
 */
@Data
@Accessors(chain = true)
public class Message implements Serializable {
    private String sender;
    private String receiver;
    private String message;
    private Date sendTime;
    private String tag;
}
