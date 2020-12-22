package indi.ikun.spring.provider.service.demo.vo;


import indi.ikun.spring.provider.service.demo.config.StatusCodeEnum;

/**
 * 便捷的rest响应结果工具类
 */
public final class Result {

    private static final DefaultResultBean RESPONSE_MESSAGE_SUCCESS = new DefaultResultBean().success();

    public static DefaultResultBean success() {
        return RESPONSE_MESSAGE_SUCCESS;
    }

    public static <T> DefaultResultBean<T> success(T data) {
        return new DefaultResultBean<T>().success(data);
    }

    public static <T> DefaultResultBean<T> success(String userMsg, T data) {
        return new DefaultResultBean<T>().success(userMsg, data);
    }

    public static DefaultResultBean successMsg(String message) {
        return new DefaultResultBean().success(message);
    }

    public static DefaultResultBean error() {
        return error("");
    }

    public static DefaultResultBean error(String message) {
        return error(StatusCodeEnum.BUSINESS_ERROR.getCode(), message);
    }

    public static <T> DefaultResultBean<T> error(T data) {
        return message(StatusCodeEnum.BUSINESS_ERROR.getCode(), "", data);
    }

    public static DefaultResultBean error(Integer code, String message) {
        return message(code, message);
    }

    public static <T> DefaultResultBean error(Integer code, String message, T t) {
        return message(code, message, t);
    }

    public static DefaultResultBean message(Integer code, String message) {
        return message(code, message, null);
    }

    public static <T> DefaultResultBean<T> message(Integer code, String message, T t) {
        return new DefaultResultBean<T>().build(code, message, t);
    }
}
