package com.mumu.springcloud.java4doc.api;

import com.alibaba.fastjson.JSON;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @RequestMapping("/index")
    public String show() {
        System.out.println("这里是index入口");
        return "index";
    }


    @PostMapping("/word")
    public HttpServletResponse word(String imgData, HttpServletRequest httpRequest, HttpServletResponse response) {
        String fileName="test4.ftl";
        // 传递过程中  "+" 变为了 " " ，所以需要替换
        String newImageInfo = imgData.replaceAll(" ", "+");
        // 数据中：data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABI4AAAEsCAYAAAClh/jbAAA ...
        // 在"base64,"之后的才是图片信息
        String[] arr = newImageInfo.split("base64,");
        try {
            //竖表
            Map<String,String> map1 = new HashMap<String,String>();
            map1.put("name", "张三");
            map1.put("date", "2019-11-30");
            map1.put("value","100%");
            Map<String,String> map2 = new HashMap<String,String>();
            map2.put("name", "李四");
            map2.put("date", "2019-11-30");
            map2.put("value","90%");

            List<Map<String,String>> newlist = new ArrayList<>();
            newlist.add(map1);
            newlist.add(map2);
            //横向表
            List<Map<String,String>> hengbiao=new ArrayList<>();
            Map<String,String> map3=new HashMap<>();
            map3.put("appName","主站移动应用");
            map3.put("value2","100");
            Map<String,String> map4=new HashMap<>();
            map4.put("appName","车辆移动应用");
            map4.put("value2","79");
            hengbiao.add(map3);
            hengbiao.add(map4);

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userList", newlist);
            dataMap.put("datetime", "2019年11月");
            dataMap.put("hengbiao",hengbiao);
            dataMap.put("imgData",arr[1]);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            //指定模板路径的第二种方式,我的路径是D:/      还有其他方式
            configuration.setDirectoryForTemplateLoading(new ClassPathResource("java4word").getFile());
//            configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile("classpath:java4word"));
//            configuration.setDirectoryForTemplateLoading(new File("D:\\secret\\github\\spring\\java4doc\\src\\main\\resources\\java4word"));

            // 输出文档路径及名称java4word
//            File outFile = new File("D:/test3.doc");
            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate(fileName,"utf-8");

            String fileName1 = URLEncoder.encode("test","UTF-8") + dataMap.get("formCode")+".doc";
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("application/x-download");
//            response.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName1);
            Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"),10240);
            t.process(dataMap, out);
            out.close();
//            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
//            t.process(dataMap, out);
//            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * @TODO // 利用ftl模板生成word文件字节
     * @Author:renqiankun
     * @Date:2019-11-27 09:32
     * @param:
     * @return:
     **/
    public byte[] createWordByFTL(String dir,String fileName,Map<String,Object> data){
        byte[] bytes=null;
//        Configuration configuration=null;
//        Template t=null;
//        Writer out=null;
//        try {
//            bytes=new byte[66];
//            configuration = new Configuration();
//            configuration.setDefaultEncoding("utf-8");
//            configuration.setDirectoryForTemplateLoading(new ClassPathResource(dir).getFile());
//            //以utf-8的编码读取ftl文件
//            t =  configuration.getTemplate(fileName,"utf-8");
//
//            out=new BufferedWriter(new OutputStreamWriter(new ByteArrayOutputStream(),"utf-8"),10240);
////            out=new BufferedWriter(new OutputStreamWriter(bytes,""))
//            t.process(data,out);
//
//            bytes=out.write();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }catch (TemplateException e){
//            e.printStackTrace();
//        }



        return bytes;
    }
}