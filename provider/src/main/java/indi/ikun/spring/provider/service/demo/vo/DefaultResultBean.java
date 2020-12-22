package indi.ikun.spring.provider.service.demo.vo;

import indi.ikun.spring.provider.service.demo.config.StatusCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 接口返回类：普通
 * 仅限用于Controller，不允许传递跨层！！
 * 为什么要统一返回一个ResultBean：为了统一格式，为了应用AOP，为了包装异常信息
 */
public class DefaultResultBean<T> extends AbstractResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1831987037266578706L;

    @SuppressWarnings({"unchecked"})
    @Override
    public DefaultResultBean<T> build(int statusCode, String statusMsg, T data, T extend) {
        this.setCode(statusCode);
        this.setMsg(statusMsg);
        this.setData(data == null ? (T) new EmptyObject() : data);
        this.setExtend(extend == null ? new EmptyObject() : extend);
        return this;
    }

    @Override
    public DefaultResultBean<T> build(int statusCode, String statusMsg, T data) {
        return build(statusCode, statusMsg, data, null);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public DefaultResultBean<T> build(int statusCode, String statusMsg) {
        this.setCode(statusCode);
        this.setMsg(statusMsg);
        if (this.getData() == null) {
            this.setData((T) new EmptyObject());
        }
        if (this.getExtend() == null) {
            this.setExtend(new EmptyObject());
        }
        return this;
    }


    @Override
    public DefaultResultBean<T> success() {
        StatusCodeEnum success = StatusCodeEnum.SUCCESS;
        return build(success.getCode(), success.getMsgCN());
    }

    @Override
    public DefaultResultBean<T> success(T data) {
        StatusCodeEnum success = StatusCodeEnum.SUCCESS;
        return success(success.getMsgCN(), data);
    }

    @Override
    public DefaultResultBean<T> success(String userMsg) {
        StatusCodeEnum success = StatusCodeEnum.SUCCESS;
        return build(success.getCode(), userMsg);
    }

    @Override
    public DefaultResultBean<T> success(String userMsg, T data) {
        StatusCodeEnum success = StatusCodeEnum.SUCCESS;
        return build(success.getCode(), userMsg, data, null);
    }


    @Override
    public DefaultResultBean<T> failed() {
        StatusCodeEnum failed = StatusCodeEnum.FAILED;
        return failed(failed.getMsgCN());
    }

    @Override
    public DefaultResultBean<T> failed(String userMsg) {
        StatusCodeEnum failed = StatusCodeEnum.FAILED;
        return build(failed.getCode(), userMsg);
    }


    @Override
    public DefaultResultBean<T> dataNotFound() {
        StatusCodeEnum dataNotFound = StatusCodeEnum.DATA_NOT_FOUND;
        return dataNotFound(dataNotFound.getMsgCN());
    }

    @Override
    public DefaultResultBean<T> dataNotFound(String userMsg) {
        StatusCodeEnum dataNotFound = StatusCodeEnum.DATA_NOT_FOUND;
        return build(dataNotFound.getCode(), userMsg);
    }


    @Override
    public DefaultResultBean<T> businessError() {
        StatusCodeEnum bizError = StatusCodeEnum.BUSINESS_ERROR;
        return build(bizError.getCode(), bizError.getMsgCN());
    }

    @Override
    public DefaultResultBean<T> businessError(Exception be) {
        StatusCodeEnum bizError = StatusCodeEnum.BUSINESS_ERROR;
        String userMsg = StringUtils.isBlank(be.getMessage()) ? bizError.getMsgCN() : be.getMessage();
        return build(bizError.getCode(), userMsg);
    }

    @Override
    public DefaultResultBean<T> businessError(String userMsg) {
        StatusCodeEnum bizError = StatusCodeEnum.BUSINESS_ERROR;
        return build(bizError.getCode(), userMsg);
    }


    @Override
    public DefaultResultBean<T> internalError() {
        StatusCodeEnum interError = StatusCodeEnum.INTERNAL_ERROR;
        return build(interError.getCode(), interError.getMsgCN());
    }

    @Override
    public DefaultResultBean<T> internalError(String userMsg) {
        StatusCodeEnum interError = StatusCodeEnum.INTERNAL_ERROR;
        return build(interError.getCode(), userMsg);
    }

    @Override
    public DefaultResultBean<T> internalError(Throwable t) {
        StatusCodeEnum interError = StatusCodeEnum.INTERNAL_ERROR;
        String userMsg = StringUtils.isBlank(t.getMessage()) ? interError.getMsgCN() : t.getMessage();
        return build(interError.getCode(), userMsg);
    }

}
