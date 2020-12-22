package indi.ikun.spring.provider.service.demo.service.impl;

import com.google.common.base.Joiner;
import indi.ikun.spring.provider.service.demo.service.ShareServiceData;
import indi.ikun.spring.provider.service.demo.service.builder.builders.*;
import indi.ikun.spring.provider.service.demo.service.builder.director.KPIDataBuildDirect;
import indi.ikun.spring.provider.service.demo.service.builder.KPIDataBuilder;
import indi.ikun.spring.provider.service.demo.vo.BaseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class ShareServiceDataImpl implements ShareServiceData {

    private LeftOneBuilder leftOneBuilder;
    private LeftTwoBuilder leftTwoBuilder;
    private LeftThreeBuilder leftThreeBuilder;
    private RightTwoBuilder rightTwoBuilder;
    private RightThreeBuilder rightThreeBuilder;
    private RightFourBuilder rightFourBuilder;
    private CenterThreeLeftBuilder centerThreeLeftBuilder;
    private CenterThreeRightBuilder centerThreeRightBuilder;

    private CenterOneBuilder centerOneBuilder;

    @Autowired
    public ShareServiceDataImpl(
            LeftOneBuilder leftOneBuilder,
            LeftTwoBuilder leftTwoBuilder,
            LeftThreeBuilder leftThreeBuilder,
            CenterOneBuilder centerOneBuilder,
            RightTwoBuilder rightTwoBuilder,
            RightThreeBuilder rightThreeBuilder,
            RightFourBuilder rightFourBuilder,
            CenterThreeLeftBuilder centerThreeLeftBuilder,
            CenterThreeRightBuilder centerThreeRightBuilder
    ) {
        Assert.notNull(leftOneBuilder, "leftOneBuilder must be not null");
        Assert.notNull(leftTwoBuilder, "leftTwoBuilder must be not null");
        Assert.notNull(leftThreeBuilder, "leftThreeBuilder must be not null");
        Assert.notNull(rightTwoBuilder, "rightTwoBuilder must be not null");
        Assert.notNull(rightThreeBuilder, "rightThreeBuilder must be not null");
        Assert.notNull(rightFourBuilder, "rightFourBuilder must be not null");
        Assert.notNull(centerThreeLeftBuilder, "centerThreeLeftBuilder must be not null");
        Assert.notNull(centerThreeRightBuilder, "centerThreeRightBuilder must be not null");
        Assert.notNull(centerOneBuilder, "centerOneBuilder must be not null");
        this.leftOneBuilder = leftOneBuilder;
        this.leftTwoBuilder = leftTwoBuilder;
        this.leftThreeBuilder = leftThreeBuilder;
        this.rightTwoBuilder = rightTwoBuilder;
        this.rightThreeBuilder = rightThreeBuilder;
        this.rightFourBuilder = rightFourBuilder;
        this.centerThreeLeftBuilder = centerThreeLeftBuilder;
        this.centerThreeRightBuilder = centerThreeRightBuilder;
        this.centerOneBuilder = centerOneBuilder;
    }

    @Override
    public List<BaseData> getAll() {
        //返回值
        List<BaseData> result = new ArrayList<>();

        int num=9;
        //一个开始指令
        CountDownLatch begin=new CountDownLatch(1);
        //多个结束指令
        CountDownLatch end = new CountDownLatch(num);
        //每个建造指挥者一个线程
        ExecutorService es = Executors.newFixedThreadPool(num);
        //记录每个线程返回的结果
        List<Future<BaseData>> futures=new ArrayList<>();

        //累计服务调用次数
        futures.add(es.submit(getKpiDataBuildDirect(leftOneBuilder,begin,end)));
        //服务调用分布+中心六边形能力图
        futures.add(es.submit(getKpiDataBuildDirect(leftTwoBuilder,begin,end)));
        //平均响应时间
        futures.add(es.submit(getKpiDataBuildDirect(leftThreeBuilder,begin,end)));
        //中台建设情况
        futures.add(es.submit(getKpiDataBuildDirect(centerOneBuilder,begin,end)));
        //服务调用时段分析
        futures.add(es.submit(getKpiDataBuildDirect(centerThreeLeftBuilder,begin,end)));
        //app调用情况
        futures.add(es.submit(getKpiDataBuildDirect(centerThreeRightBuilder,begin,end)));
        //服务接口排行
        futures.add(es.submit(getKpiDataBuildDirect(rightTwoBuilder,begin,end)));
        //慢接口汇总
        futures.add(es.submit(getKpiDataBuildDirect(rightThreeBuilder,begin,end)));
        //异常接口汇总
        futures.add(es.submit(getKpiDataBuildDirect(rightFourBuilder,begin,end)));

        //下达开始并行查询数据命令
        begin.countDown();
        //等待所有线程执行完毕
        try {
            //线程等待业务执行1分钟，否则超时，防止业务死锁
            if(end.await(1, TimeUnit.MINUTES)){
                //转换返回值
                for (Future<BaseData> bs:
                        futures ) {
                    result.add(bs.get());
                }
            }else {
                log.error("the getKpiData task [{}] is timeout",Thread.currentThread().getName());
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("业务异常\n异常信息：{}",e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            //释放资源
            es.shutdown();
        }
        log.info("get data success \n{}",Joiner.on("\n").skipNulls().join(result));
        return result;
    }

    /**
     * 组装指挥者
     * @param kpiDataBuilder 具体建造者
     * @param begin 开始计数器
     * @param end 结束计数器
     * @return 返回一个指挥者
     */
    private KPIDataBuildDirect getKpiDataBuildDirect(
            KPIDataBuilder kpiDataBuilder
            , CountDownLatch begin
            , CountDownLatch end
    ){
        return new KPIDataBuildDirect(kpiDataBuilder,begin,end);
    }
}
