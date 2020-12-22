package indi.ikun.spring.provider.service.demo.service.builder.director;

import indi.ikun.spring.provider.service.demo.service.builder.KPIDataBuilder;
import indi.ikun.spring.provider.service.demo.vo.BaseData;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class KPIDataBuildDirect implements Callable<BaseData> {

    private KPIDataBuilder kpiDataBuilder;
    //开始信号
    private CountDownLatch begin;
    //结束信号
    private CountDownLatch end;

    public KPIDataBuildDirect(
            KPIDataBuilder kpiDataBuilder
            ,CountDownLatch begin
            ,CountDownLatch end
    ) {
        this.kpiDataBuilder = kpiDataBuilder;
        this.begin = begin;
        this.end = end;
    }

    public BaseData build() {
        kpiDataBuilder.setKey();
        kpiDataBuilder.setValue();
        return kpiDataBuilder.build();
    }

    @Override
    public BaseData call() throws Exception {
        //等待开始命令
        begin.await();
        log.info("the getKpiData task [{}-{}] is start",kpiDataBuilder.toString(),Thread.currentThread().getName());
        BaseData build=build();
        end.countDown();
        log.info("the getKpiData task [{}-{}] is finished. the result is {}"
                ,kpiDataBuilder.toString()
                ,Thread.currentThread().getName()
                ,build.toString()
        );
        return build;
    }
}
