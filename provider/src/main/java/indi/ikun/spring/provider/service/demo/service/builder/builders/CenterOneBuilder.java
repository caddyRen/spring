package indi.ikun.spring.provider.service.demo.service.builder.builders;

import indi.ikun.spring.provider.service.demo.service.builder.KPIDataBuilder;
import indi.ikun.spring.provider.service.demo.vo.BaseData;
import org.springframework.stereotype.Component;

@Component
public class CenterOneBuilder extends KPIDataBuilder {

    @Override
    public void setKey() {
        super.getBaseData().setKey("CenterOneBuilder");
    }

    @Override
    public void setValue() {
        try {
            Thread.sleep(70*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.getBaseData().setValue(1);
    }

    @Override
    public BaseData build() {
        return super.build();
    }

}
