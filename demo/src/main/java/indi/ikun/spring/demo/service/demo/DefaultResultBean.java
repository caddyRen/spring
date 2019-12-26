package indi.ikun.spring.demo.service.demo;

import indi.ikun.spring.demo.service.EmptyObject;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author : York. Create date: 2018/5/31 18:01.
 */

public class DefaultResultBean<T> extends AbstractResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1831987037266578706L;

    @Override
    public DefaultResultBean<T> build(int statusCode, String statusMsg, T data, T extend) {
        this.setCode(statusCode);
        this.setMsg(statusMsg);
        this.setData(data == null ? (T) new EmptyObject() : data);
        this.setExtend(extend == null ? new EmptyObject() : extend);
        return this;
    }

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

    // -------- Success --------

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
    public DefaultResultBean<T> successWithMsg(String userMsg) {
        StatusCodeEnum success = StatusCodeEnum.SUCCESS;
        return build(success.getCode(), userMsg);
    }

    @Override
    public DefaultResultBean<T> success(String userMsg, T data) {
        StatusCodeEnum success = StatusCodeEnum.SUCCESS;
        return build(success.getCode(), userMsg, data, null);
    }

    // -------- Failed --------

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

    // -------- Data Not Found --------

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

    // -------- Business Error --------

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

    // -------- Internal Error --------

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
