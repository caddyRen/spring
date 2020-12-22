package indi.ikun.spring.provider;

import indi.ikun.spring.provider.service.demo.service.ShareServiceData;
import indi.ikun.spring.provider.service.demo.vo.BaseData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProviderApplicationTests {

    @Autowired
    ShareServiceData shareServiceData;

    @Test
    public void test(){
        List<BaseData> all = shareServiceData.getAll();
        System.err.println(all);
        assertThat("Hello World").isEqualTo("Hello World");
    }

}
