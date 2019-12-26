package indi.ikun.spring.demo.service.demo;

/**
 * @author : York. Create date: 2018/5/31 18:04.
 */

public enum StatusCodeEnum {
    FAILED(0, "请求处理失败", "Request Failed."),

    SUCCESS(1, "请求处理成功", "Request Success."),

    BUSINESS_ERROR(4000, "请求出错，请重试", "Request failed, retry please."),

    DATA_NOT_FOUND(4004, "没有更多数据", "No Data Found."),

    INTERNAL_ERROR(5000, "服务器出问题了，请稍后重试", "Something on server went wrong, please retry later."),

    RES_NOT_FOUND(5004, "您请求的资源不存在", "Sorry, resource you need is not exist.");

    private int code;
    private String msgCN;
    private String msgEN;

    private StatusCodeEnum(int code, String msgCN, String msgEN) {
        this.code = code;
        this.msgCN = msgCN;
        this.msgEN = msgEN;
    }

    public int getCode() {
        return code;
    }

    public String getMsgCN() {
        return msgCN;
    }

    public String getMsgEN() {
        return msgEN;
    }

    public StatusCodeEnum getByKey(int code) {
        for (StatusCodeEnum c : StatusCodeEnum.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return null;
    }
}
