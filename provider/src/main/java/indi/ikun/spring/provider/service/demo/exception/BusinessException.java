package indi.ikun.spring.provider.service.demo.exception;


import indi.ikun.spring.provider.service.demo.config.StatusCodeEnum;

/**
 * 业务处理异常类
 * @author renqiankun
 */
public class BusinessException extends RuntimeException {

    private Integer errCode = StatusCodeEnum.FAILED.getCode();

    private String errMsg = StatusCodeEnum.FAILED.getMsgCN();

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(Integer errCode) {
        super(String.valueOf(errCode));
        this.errCode = errCode;
    }

    public BusinessException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public BusinessException(Integer errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessException(Integer errCode, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessException(Integer errCode, String errMsg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errMsg, cause, enableSuppression, writableStackTrace);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessException(StatusCodeEnum statusCodeEnum) {
        super(statusCodeEnum.getMsgEN());
        this.errCode = statusCodeEnum.getCode();
        this.errMsg = statusCodeEnum.getMsgCN();
    }

}
