package lz.com.client;


import lz.com.config.DefaultFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */

@FeignClient(value = "",configuration = DefaultFeignConfig.class)
public interface UserClient {

    /**
     *
     * @param id 用户id
     * @return 返回
     */
    @GetMapping("/")
    String query(@RequestParam("ids") String id);
}
