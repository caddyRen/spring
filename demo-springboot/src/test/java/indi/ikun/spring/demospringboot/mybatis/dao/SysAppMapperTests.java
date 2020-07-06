package indi.ikun.spring.demospringboot.mybatis.dao;

import indi.ikun.spring.demospringboot.mybatis.po.SysApp;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SysAppMapperTests {

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private SysAppMapper sysAppMapper;

    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(SysApp.builder().appName("主站").appShortName("xcgk").build());
        SysApp user = this.sysAppMapper.getByAppId2("xcgk");
        assertThat(user.getAppName()).isEqualTo("主站");
        assertThat(user.getAppShortName()).isEqualTo("xcgk");
    }
}
