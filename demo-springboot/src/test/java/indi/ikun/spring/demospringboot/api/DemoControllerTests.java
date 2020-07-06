package indi.ikun.spring.demospringboot.api;

import indi.ikun.spring.demospringboot.mybatis.dao.SysAppMapper;
import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)//测试controller
public class DemoControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SysAppMapper sysAppMapper;
    @Test
    public void exampleTest() throws Exception{
        given(this.sysAppMapper.getByAppId2("xcgk")).willReturn(SysApp.builder().appName("主站").build());
        this.mvc.perform(get("/index2").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("Hello World"));
    }
}
