package indi.ikun.spring.demo.service.demo;

/**
 * @author : York. Create date: 2018/5/31 18:02.
 */

public abstract class AbstractResultBean<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 消息，用于提示
     */
    private String msg;
    /**
     * 数据对象
     */
    private T data;
    /**
     * 消息扩展
     */
    private Object extend;

    /**
     * 完全自定义构造一个ResultBean
     *
     * @param code
     * @param msg
     * @param data
     * @param extend
     *
     * @return
     */
    public abstract AbstractResultBean<T> build(int code, String msg, T data, T extend);

    /**
     * 构造一个ResultBean
     *
     * @param code
     * @param msg
     *
     * @return
     */
    public abstract AbstractResultBean<T> build(int code, String msg);
    // -------- Success--------

    /**
     * 构造一个默认的请求成功ResultBean，data 为空对象（非 null）
     *
     * @return
     */
    public abstract AbstractResultBean<T> success();

    /**
     * 使用自定义的 msg 构建一个请求成功的返回对象
     */
    public abstract AbstractResultBean<T> successWithMsg(String msg);

    /**
     * 根据传入的对象构造一个请求成功ResultBean
     *
     * @param data 数据对象
     *
     * @return
     */
    public abstract AbstractResultBean<T> success(T data);

    /**
     * 根据传入的 data 对象和 msg 构造一个请求成功的 ResultBean
     *
     * @param data 数据对象
     * @param msg  用户提示消息
     *
     * @return
     */
    public abstract AbstractResultBean<T> success(String msg, T data);
    // -------- Failed --------

    /**
     * 构造一个默认的失败数据 ResultBean，data 为空对象（非 null）
     *
     * @return
     */
    public abstract AbstractResultBean<T> failed();

    /**
     * 根据传入的 msg 构造一个失败数据的 ResultBean
     *
     * @param msg 用户提示消息
     *
     * @return
     */
    public abstract AbstractResultBean<T> failed(String msg);
    // -------- Data Not Found --------

    /**
     * 构造一个默认的未找到数据 ResultBean，data 为空对象（非 null）
     *
     * @return
     */
    public abstract AbstractResultBean<T> dataNotFound();

    /**
     * 根据传入的 msg 构造一个未找到数据的 ResultBean
     *
     * @param msg 用户提示消息
     *
     * @return
     */
    public abstract AbstractResultBean<T> dataNotFound(String msg);
    // -------- Business Error --------

    /**
     * 构造一个默认的业务错误 ResultBean，data 为空对象（非 null）
     *
     * @return
     */
    public abstract AbstractResultBean<T> businessError();

    /**
     * 根据传入的 Exception 构造一个业务错误 ResultBean
     *
     * @param be 业务错误异常类，用于 msg
     *
     * @return
     */
    public abstract AbstractResultBean<T> businessError(Exception be);

    /**
     * 根据传入的 msg 构造一个业务错误 ResultBean
     *
     * @param msg 提示消息
     *
     * @return
     */
    public abstract AbstractResultBean<T> businessError(String msg);
    // -------- Internal Error --------

    /**
     * 构造一个默认的服务器错误 ResultBean
     *
     * @return
     */
    public abstract AbstractResultBean<T> internalError();

    /**
     * 根据传入的 msg 构造一个服务器错误的 ResultBean
     *
     * @param msg 用户提示消息
     *
     * @return
     */
    public abstract AbstractResultBean<T> internalError(String msg);

    /**
     * 根据传入的 Throwable 构造一个服务器错误的 ResultBean
     *
     * @param t 异常
     *
     * @return
     */
    public abstract AbstractResultBean<T> internalError(Throwable t);
    // -------- 扩展方法 --------

    /**
     * 传入扩展数据
     *
     * @param extend
     *
     * @return
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
            throw new RuntimeException(resultBean.getMsg());
        }
    }

    // ------------------ setter & getter ------------------
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

    @Override
    public String toString() {
        return "AbstractResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", extend=" + extend +
                '}';
    }
}
