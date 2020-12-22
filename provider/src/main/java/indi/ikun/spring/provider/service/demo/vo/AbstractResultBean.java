package indi.ikun.spring.provider.service.demo.vo;

import indi.ikun.spring.provider.service.demo.config.StatusCodeEnum;
import indi.ikun.spring.provider.service.demo.exception.BusinessException;

/**
 * 统一的返回对象基础类
 * 定义基础的 4 个字段，以及一些构建方法
 *
 * @author renqiankun
 */
public abstract class AbstractResultBean<T> {

    //状态码
    private int code;
    //提示消息
    private String msg;
    //数据对象
    private T data;
    //扩展消息
    private Object extend;

    /**
     * 完全自定义构造一个ResultBean
     *
     * @param code   状态码
     * @param msg    提示消息
     * @param data   数据对象
     * @param extend 扩展消息
     * @return ResultBean
     */
    public abstract AbstractResultBean<T> build(int code, String msg, T data, T extend);

    /**
     * 构造一个ResultBean
     *
     * @param code 状态码
     * @param msg  提示消息
     * @param data 数据对象
     * @return ResultBean
     */
    public abstract AbstractResultBean<T> build(int code, String msg, T data);

    /**
     * 构造一个ResultBean
     *
     * @param code 状态码
     * @param msg  提示消息
     * @return ResultBean
     */
    public abstract AbstractResultBean<T> build(int code, String msg);


    /**
     * 构造一个默认的请求成功的ResultBean，data 为非 null 的空对象
     *
     * @return 请求成功的ResultBean
     */
    public abstract AbstractResultBean<T> success();

    /**
     * 使用自定义的 msg 构建一个请求成功的返回对象
     *
     * @return 请求成功的返回对象
     */
    public abstract AbstractResultBean<T> success(String msg);


    /**
     * 根据传入的对象构造一个请求成功ResultBean
     *
     * @param data 数据对象
     * @return 请求成功ResultBean
     */
    public abstract AbstractResultBean<T> success(T data);


    /**
     * 根据传入的 data 对象和 msg 构造一个请求成功的 ResultBean
     *
     * @param data 数据对象
     * @param msg  提示消息
     * @return 请求成功的 ResultBean
     */
    public abstract AbstractResultBean<T> success(String msg, T data);


    /**
     * 构造一个默认的失败数据 ResultBean，data 为非 null 的空对象
     *
     * @return 失败数据 ResultBean
     */
    public abstract AbstractResultBean<T> failed();

    /**
     * 根据传入的 msg 构造一个失败数据的 ResultBean
     *
     * @param msg 提示消息
     * @return 失败数据的 ResultBean
     */
    public abstract AbstractResultBean<T> failed(String msg);


    /**
     * 构造一个默认的未找到数据 ResultBean，data 为非 null 的空对象
     *
     * @return 未找到数据 ResultBean
     */
    public abstract AbstractResultBean<T> dataNotFound();

    /**
     * 根据传入的 msg 构造一个未找到数据的 ResultBean
     *
     * @param msg 提示消息
     * @return 未找到数据的 ResultBean
     */
    public abstract AbstractResultBean<T> dataNotFound(String msg);


    /**
     * 构造一个默认的业务错误 ResultBean，data 为非 null 的空对象
     *
     * @return 业务错误 ResultBean
     */
    public abstract AbstractResultBean<T> businessError();

    /**
     * 根据传入的 Exception 构造一个业务错误 ResultBean
     *
     * @param e 业务错误异常类，用于 msg
     * @return 业务错误 ResultBean
     */
    public abstract AbstractResultBean<T> businessError(Exception e);

    /**
     * 根据传入的 msg 构造一个业务错误 ResultBean
     *
     * @param msg 提示消息
     * @return 业务错误 ResultBean
     */
    public abstract AbstractResultBean<T> businessError(String msg);

    /**
     * 构造一个默认的服务器错误 ResultBean
     *
     * @return 服务器错误 ResultBean
     */
    public abstract AbstractResultBean<T> internalError();

    /**
     * 根据传入的 msg 构造一个服务器错误的 ResultBean
     *
     * @param msg 用户提示消息
     * @return 服务器错误 ResultBean
     */
    public abstract AbstractResultBean<T> internalError(String msg);

    /**
     * 根据传入的 Throwable 构造一个服务器错误的 ResultBean
     *
     * @param t 异常
     * @return 服务器错误 ResultBean
     */
    public abstract AbstractResultBean<T> internalError(Throwable t);

    /**
     * 带扩展数据的ResultBean
     *
     * @param extend 扩展数据
     * @return 带扩展数据的ResultBean
     */
    public AbstractResultBean<T> extend(Object extend) {
        this.extend = extend;
        return this;
    }

    /**
     * 校验对象是否请求成功的返回
     *
     * @param resultBean 待校验的 ResultBean 对象
     */
    public static void validateJsonSuccess(AbstractResultBean resultBean) {
        if (StatusCodeEnum.SUCCESS.getCode() != resultBean.getCode()) {
            throw new BusinessException();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }
}
