package indi.ikun.spring.ymldata.service.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @ClassName YmldataContraller
 * @Description TODO
 * @Author caddyR
 * @Date 2019-09-17 11:24
 * @Version 1.0
 **/
@RestController
@RequestMapping("ymldata")
@Slf4j
public class YmldataContraller {
    @Value("${kpi}")
    String[] kpi;

    @Value("${assets-system-edge-service}")
    String[] assetsSystemEdge;

    @Value("${property-ledger-service}")
    String[] propertyLedger;

    @GetMapping("/propertyLedger")
    public String getPropertyLedger(){
        String PROPERTYLEDGER="property-ledger-service";
        String result="";
        for(int i=0;i<propertyLedger.length;i++){
            String path=PROPERTYLEDGER+"/"+propertyLedger[i];
            ClassPathResource classPathResource = new ClassPathResource(path);
            try {
                InputStream inputStream =classPathResource.getInputStream();
                Yaml yaml=new Yaml();
                Map<String,Object> map =yaml.load(inputStream);
                System.err.println(map.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;






//        InputStream ins = this.getClass().getClassLoader().getResourceAsStream(path);
//        File dirFile= new File(String.valueOf(ins));
//
//        if(!dirFile.exists()){
//            log.info(path+"do not exist");
//            return path+"do not exist";
//        }else if(dirFile.isDirectory()){
//            String[] fileList=dirFile.list();
//            for(int i=0;i<fileList.length;i++){
//                File file=new File(dirFile.getPath(),fileList[i]);
//                Yaml yaml = new Yaml();
//                try {
//                    InputStream in = new FileInputStream(file);
//                    Map<String,Object> map =yaml.load(in);
//                    System.out.println(map.size());
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//            return result;
//        }else{
//            File file=new File(dirFile.getPath());
//            Yaml yaml = new Yaml();
//            try {
//                InputStream in = new FileInputStream(file);
//                Map<String,Object> map =yaml.load(in);
//                System.out.println(map.size());
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//            return result;
//        }
    }

    @ApiOperation(value = "单文件上传")
    @PostMapping(value = "/uploadYmlFile")
    public String uploadYmlFile(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(value = "上传的文件",required = true) MultipartFile multipartFile) {

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFiles = multipartHttpServletRequest.getFiles("multipartFile");

        if (multipartFiles.size() > 0) {
            for (MultipartFile file : multipartFiles) {
                if (file.isEmpty()) {
                    continue;
                }else {
                    Yaml yaml = new Yaml();
                    try {
                        InputStream in = file.getInputStream();
                        Map<String,Object> map =yaml.load(in);
                        System.out.println(map.size());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return "ok";

    }
    @ApiOperation(value = "多文件上传")
    @PostMapping(value = "/uploadYmlfiles")
    public String uploadYmlfiles(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(value = "上传的文件夹",required = true) MultipartFile[] multipartFile) {

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFiles = multipartHttpServletRequest.getFiles("multipartFile");

        if (multipartFiles.size() > 0) {
            for (MultipartFile file : multipartFiles) {
                if (file.isEmpty()) {
                    continue;
                }else {
                    Yaml yaml = new Yaml();
                    try {
                        InputStream in = file.getInputStream();
                        Map<String,Object> map =yaml.load(in);
                        System.out.println(map.size());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return "ok";
    }


}
