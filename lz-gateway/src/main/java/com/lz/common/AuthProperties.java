package com.lz.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/03/05
 */
@Component
@Data
public class AuthProperties {
    /**
     * 需要校验的路径
     */
    private List<String> includePaths= new ArrayList<>();

    /**
     * 不用校验的路径
     */

    @Value("${spring.security.excludePaths}")
    private List<String> excludePaths;
}
