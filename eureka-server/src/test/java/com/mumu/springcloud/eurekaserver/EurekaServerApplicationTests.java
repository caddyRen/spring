package com.mumu.springcloud.eurekaserver;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
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
        //加密
        String result=stringEncryptor.encrypt("47.112.26.145");
//        String result=stringEncryptor.encrypt("jdbc:mysql://localhost:3306/message");
//        String result=stringEncryptor.encrypt("root");
//        String result=stringEncryptor.encrypt("root");
        System.err.println(result);
        //System.err.println(stringEncryptor.decrypt("azPzr/f9LSM+a8eQo37Rpou5n4+9Hjck"));
        System.err.println("-------------------------------------------------------------------");
        System.err.println(stringEncryptor.decrypt("LwDq7cVAjj23B7QnBhICXw=="));
        System.err.println(stringEncryptor.decrypt("6/KHq+EMolIWnE0JJV+LjYm24D4ie8lu"));
        System.err.println(stringEncryptor.decrypt("yfuirQOwkBtgvXSlD3L5RhshOLO1CDzagqjrdHh3Hk5II0d5d48PyZwNjIusQFTaJvaxQRn28KVOpLcIGbNnpg=="));
        //解密
        //System.err.println(stringEncryptor.decrypt("4DFtps/COWEQB49PoqKefA9iNmEVlaRc2OGfD1RKWDRd/hy/7GPpqQmcnPbpXij3lsUK3gAYOFIeWc/wLoCN04op7T5Ppv3H+/hfmGfcq5rDBNGLCc0qnil82ouliTHI"));
    }

}
