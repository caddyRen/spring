package indi.ikun.spring.demospringboot.mybatis.po;

import indi.ikun.spring.demospringboot.mybatis.dao.SysAppMapper;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.*;
import org.springframework.boot.test.json.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@JsonTest
public class DAOTests {

    @MockBean
    private SysAppMapper sysAppMapper;

    @Autowired
    private JacksonTester<JSONTestsPO> json;

    @Test
    public void testSerialize() throws Exception {
        JSONTestsPO details = new JSONTestsPO("Ford", "Focus");
        //indi\ikun\spring\demospringboot\mybatis\po\test.json
        assertThat(this.json.write(details)).isEqualToJson("test.json");
        System.err.println(this.json.write(details));
        assertThat(this.json.write(details)).hasJsonPathStringValue("@.name");
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.name").isEqualTo("Ford");
    }
    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"name\":\"Ford\",\"text\":\"Focus\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new JSONTestsPO("Ford", "Focus"));
        assertThat(this.json.parseObject(content).getName()).isEqualTo("Ford");
        System.err.println(this.json.parse(content));
        System.err.println(this.json.parseObject(content).getName());
    }
}
