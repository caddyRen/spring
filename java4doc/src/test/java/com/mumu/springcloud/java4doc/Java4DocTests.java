package com.mumu.springcloud.java4doc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Java4DocTests {

    String fileName="test2.ftl";
    @Test
    public void test(){
        try {
            Map<String,String> dataMap = new HashMap<String,String>();
            dataMap.put("datetime", "2019年11月");
            dataMap.put("name", "张三");
            dataMap.put("date", "2019-11-30");
            dataMap.put("value","100%");
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            //指定模板路径的第二种方式,我的路径是D:/      还有其他方式
            configuration.setDirectoryForTemplateLoading(new ClassPathResource("java4word").getFile());
            // 输出文档路径及名称
            File outFile = new File("D:/test.doc");
            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate(fileName,"utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(dataMap, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try {
            Map<String,String> map1 = new HashMap<String,String>();
//            map1.put("datetime", "2019年11月");
            map1.put("name", "张三");
            map1.put("date", "2019-11-30");
            map1.put("value","100%");
            Map<String,String> map2 = new HashMap<String,String>();
//            map2.put("datetime", "2019年11月");
            map2.put("name", "张三");
            map2.put("date", "2019-11-30");
            map2.put("value","100%");

            List<Map<String,String>> newlist = new ArrayList<>();
            newlist.add(map1);
            newlist.add(map2);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userList", newlist);
            dataMap.put("datetime", "2019年11月");
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            //指定模板路径的第二种方式,我的路径是D:/      还有其他方式
            configuration.setDirectoryForTemplateLoading(new File("D:\\secret\\github\\spring\\java4doc\\src\\main\\resources\\java4word"));

            // 输出文档路径及名称
            File outFile = new File("D:/test.doc");
            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate(fileName,"utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(dataMap, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        try {
            //竖表
            Map<String,String> map1 = new HashMap<String,String>();
            map1.put("name", "张三");
            map1.put("date", "2019-11-30");
            map1.put("value","100%");
            Map<String,String> map2 = new HashMap<String,String>();
            map2.put("name", "张三");
            map2.put("date", "2019-11-30");
            map2.put("value","100%");

            List<Map<String,String>> newlist = new ArrayList<>();
            newlist.add(map1);
            newlist.add(map2);
            //横向表
            List<Map<String,String>> hengbiao=new ArrayList<>();
            Map<String,String> map3=new HashMap<>();
            map3.put("appName","主站移动应用");
            map3.put("value2","100");
            Map<String,String> map4=new HashMap<>();
            map4.put("appName","主站移动应用");
            map4.put("value2","100");
            hengbiao.add(map3);
            hengbiao.add(map4);

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userList", newlist);
            dataMap.put("datetime", "2019年11月");
            dataMap.put("hengbiao",hengbiao);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            //指定模板路径的第二种方式,我的路径是D:/      还有其他方式
//            configuration.setDirectoryForTemplateLoading(new File("classpath:java4word"));
            configuration.setDirectoryForTemplateLoading(new File("D:\\secret\\github\\spring\\java4doc\\src\\main\\resources\\java4word"));

            // 输出文档路径及名称
            File outFile = new File("D:/test3.doc");
            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate(fileName,"utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(dataMap, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
