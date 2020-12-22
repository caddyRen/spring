package indi.ikun.spring.provider.service.demo.controller;

import com.google.common.collect.ImmutableMap;
import indi.ikun.spring.provider.service.demo.service.ShareServiceData;
import indi.ikun.spring.provider.service.demo.vo.BaseData;
import indi.ikun.spring.provider.service.demo.vo.DefaultResultBean;
import indi.ikun.spring.provider.service.demo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 建造者模式
 * @author renqiankun
 */
@Slf4j
@RestController("用户体验-共享服务模块")
@RequestMapping("/demo/build")
public class ShareServicesDataApi {

    private ShareServiceData shareServiceData;


    @Autowired
    public ShareServicesDataApi(
            ShareServiceData shareServiceData
    ) {
        Assert.notNull(shareServiceData, "shareServiceData must be not null");
        this.shareServiceData=shareServiceData;
    }


    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DefaultResultBean getShareServicesData() {
        List<BaseData> all = shareServiceData.getAll();
        if(all.size()==0){
            return Result.error("获取数据超时");
        }
        return Result.success(ImmutableMap.of("jwt", all));
    }

}
