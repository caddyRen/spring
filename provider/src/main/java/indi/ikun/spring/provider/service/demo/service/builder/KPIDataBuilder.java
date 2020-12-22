package indi.ikun.spring.provider.service.demo.service.builder;

import indi.ikun.spring.provider.service.demo.vo.BaseData;

public abstract class KPIDataBuilder {
    BaseData baseData = new BaseData();

    public abstract void setKey();

    public abstract void setValue();

    public BaseData build() {
        return baseData;
    }

    public BaseData getBaseData() {
        return baseData;
    }

    /**
     * 多线程日志输出使用此方法获取具体建造者
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
