package indi.ikun.spring.ifelse;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IfElseApplicationTests {
    @Test
    public void test(){
        String name = null;
        String name2 = null;
        String name3 = null;
        Assert.assertNotNull("姓名不能为空",name2);
        System.err.println(111);
//        Assert.assertFalse("姓名不能为空", StringUtils.isEmpty(name));


    }


}
