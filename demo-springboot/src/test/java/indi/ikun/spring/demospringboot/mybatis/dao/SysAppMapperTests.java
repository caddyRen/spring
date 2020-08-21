package indi.ikun.spring.demospringboot.mybatis.dao;

import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import org.junit.*;
import org.junit.runner.*;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.*;


/**
 * TODO: mybatis 测试dao层写法
 * TODO: jpa 测试dao层写法
 */
@RunWith(SpringRunner.class)
//@DataJpaTest
@MybatisTest
//@Import(SysAppMapper.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootApplication(scanBasePackages ="indi.ikun.spring" )
public class SysAppMapperTests {

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
//    @Autowired
    private SysAppMapper sysAppMapper;

    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(SysApp.builder().appName("主站").appShortName("xcgk").build());
        SysApp user = sysAppMapper.getByAppId2("xcgk");
        assertThat(user.getAppName()).isEqualTo("主站");
        assertThat(user.getAppShortName()).isEqualTo("xcgk");
    }
}
