package indi.ikun.spring.provider.service.demo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 为 ResultBean 准备的空对象
 */
@JsonSerialize
public class EmptyObject implements Serializable {

    private static final long serialVersionUID = -3797232489378716705L;

}
