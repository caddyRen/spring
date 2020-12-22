package indi.ikun.spring.provider.service.demo.service.builder.builders;

import indi.ikun.spring.provider.service.demo.service.builder.KPIDataBuilder;
import indi.ikun.spring.provider.service.demo.vo.BaseData;
import org.springframework.stereotype.Component;

@Component
public class RightOneBuilder extends KPIDataBuilder {

    @Override
    public void setKey() {
        super.getBaseData().setKey("RightOneBuilder");
    }

    @Override
    public void setValue() {
        super.getBaseData().setValue(8);
    }

    @Override
    public BaseData build() {
        return super.build();
    }
}
