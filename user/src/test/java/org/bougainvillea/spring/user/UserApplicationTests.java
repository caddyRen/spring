package org.bougainvillea.spring.user;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserApplicationTests {
    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    void contextLoads() {
        log.error(stringEncryptor.encrypt("12"));
    }

}
