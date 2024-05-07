import com.lz.AuthApplication;
import com.lz.mapper.AuthMapper;
import com.lz.service.AuthService;
import com.lz.service.impl.AuthServiceImpl;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author :Lictory
 * @date : 2024/04/14
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)

public class Test {

    @Autowired
    private AuthMapper authMapper;

    @org.junit.Test
    public void test(){
        String username="test3";
        System.out.println(authMapper.getByUsername(username));
    }
}
