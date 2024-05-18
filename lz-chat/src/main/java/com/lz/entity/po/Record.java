package com.lz.entity.po;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * (Record)实体类
 *
 * @author makejava
 * @since 2024-05-07 19:45:54
 */
@Data
@TableName(value="record")
public class Record implements Serializable {
    private static final long serialVersionUID = -82668824441971274L;

     /**
     主键
     */
    @TableField(value="id")
    private Integer id;

     /**
     单条聊天内容
     */
    @TableField(value="message")
    private String message;

     /**
     发送时间
     */
    @TableField(value="create_time")
    private Date createTime;

     /**
     发送人
     */
    @TableField(value="sender")
    private String sender;

     /**
     0代表非群聊,1代表群聊
     */
    @TableField(value="type")
    private Integer type;

     /**
     接受者,如果type为1则无需处理
     */
    @TableField(value="receiver")
    private String receiver;



}

