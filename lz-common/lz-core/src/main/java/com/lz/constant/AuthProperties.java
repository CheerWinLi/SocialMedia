package com.lz.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/03/05
 */
@Configuration
@ConfigurationProperties(prefix = "auth")
@Data
public class AuthProperties {
    /**
     * 需要校验的路径
     */
    private List<String> includePaths= new ArrayList<>();

    /**
     * 不用校验的路径
     */

    private List<String> excludePaths;
}
