package indi.ikun.spring.databasedepency.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * @author renqiankun
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "enable", matchIfMissing = false)
public class DbConfig {

    private static final Logger log = LoggerFactory.getLogger(DbConfig.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DbConfig(JdbcTemplate jdbcTemplate) {
        Assert.notNull(jdbcTemplate, "JdbcTemplate must not be null");
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * 用于启动时测试数据库连接
     * 若项目启动时无连接数据库，启动后再执行连接数据库，会导致druid线程池循环连接数据库
     * 若执行以下sql测试连接，连接失败则启动失败，防止账号被锁
     *
     * @author renqiankun
     */
    @PostConstruct
    public void testConnect() {
        log.info("======> Begin connect database ......");
        int result = jdbcTemplate.queryForObject("select 1 from dual", Integer.class);
        log.info("======> Connect database success: {}", result);
    }


}
