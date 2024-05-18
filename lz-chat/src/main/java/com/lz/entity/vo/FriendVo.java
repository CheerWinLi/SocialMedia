package com.lz.entity.vo;

import com.lz.entity.dto.UserInfoDto;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author :Lictory
 * @date : 2024/05/08
 */


@Data
@Accessors(chain = true)
public class FriendVo {
    private UserInfoDto userInfoDto;
    private String memo;

    /**
     * 拉黑0/取消拉黑1
     */
    private String status;
}
