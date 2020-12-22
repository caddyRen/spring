package indi.ikun.spring.provider.service.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础数据结构
 * @author renqiankun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseData {
    public String key;
    public Object value;
}
