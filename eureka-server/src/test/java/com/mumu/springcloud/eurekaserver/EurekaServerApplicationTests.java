package com.mumu.springcloud.eurekaserver;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EurekaServerApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Autowired
    StringEncryptor stringEncryptor;
    /*
     *@Author caddyR
     *@Description //加密解密相关测试，不要提交到git上
     * yml文件内配置要保持不变
     * jasypt:
            encryptor:
                  password: m88CREAHperHSVG9q8sHJHaVwnr4xNlC #密钥
     *@Date 2019-03-18 17:19
     *@Param []
     *@return void
    **/
    @Test
    public void encrytor(){


        log.info(stringEncryptor.decrypt("yfuirQOwkBtgvXSlD3L5RhshOLO1CDzagqjrdHh3Hk5II0d5d48PyZwNjIusQFTaJvaxQRn28KVOpLcIGbNnpg=="));
        log.info(stringEncryptor.encrypt("xcjk"));
        log.info(stringEncryptor.encrypt("xcjkxcjk"));


//        System.err.println(stringEncryptor.decrypt("7b+U8ybrFJoOZhHImZm0yW2LOVg8G/ViBRbaHMwmpWs0bum9ODABx/TDO0RoWdBhszkCUZ1PXPE="));
        System.err.println(stringEncryptor.encrypt("jdbc:oracle:thin:@172.16.17.18:1621:xcjk"));
        System.err.println(stringEncryptor.encrypt("epcsys"));
        System.err.println(111);
        System.err.println(stringEncryptor.encrypt("jdbc:oracle:thin:@localhost:1521:XE"));
        System.err.println(222222);
        System.err.println(stringEncryptor.encrypt("jdbc:oracle:thin:@172.16.21.50:1521:XE"));
        System.err.println(22);
        System.err.println(stringEncryptor.encrypt("xcjk"));
        System.err.println(3);
        System.err.println(stringEncryptor.encrypt("xcjkxcjk"));
        //加密
        //String result=stringEncryptor.encrypt("47.112.26.145");
//        String result=stringEncryptor.encrypt("jdbc:mysql://localhost:3306/message");
//        String result=stringEncryptor.encrypt("root");
//        String result=stringEncryptor.encrypt("root");
        //System.err.println(result);
        //System.err.println(stringEncryptor.decrypt("azPzr/f9LSM+a8eQo37Rpou5n4+9Hjck"));
//        System.err.println("-------------------------------------------------------------------");
//        System.err.println(stringEncryptor.decrypt("LwDq7cVAjj23B7QnBhICXw=="));
//        System.err.println(stringEncryptor.decrypt("6/KHq+EMolIWnE0JJV+LjYm24D4ie8lu"));
//        System.err.println(stringEncryptor.decrypt("yfuirQOwkBtgvXSlD3L5RhshOLO1CDzagqjrdHh3Hk5II0d5d48PyZwNjIusQFTaJvaxQRn28KVOpLcIGbNnpg=="));
        //解密
        //System.err.println(stringEncryptor.decrypt("4DFtps/COWEQB49PoqKefA9iNmEVlaRc2OGfD1RKWDRd/hy/7GPpqQmcnPbpXij3lsUK3gAYOFIeWc/wLoCN04op7T5Ppv3H+/hfmGfcq5rDBNGLCc0qnil82ouliTHI"));
    }

}
