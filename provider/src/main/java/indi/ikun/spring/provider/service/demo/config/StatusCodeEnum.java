package indi.ikun.spring.provider.service.demo.config;

/**
 * 返回的状态码与默认消息枚举类
 * @author renqiankun
 */
public enum StatusCodeEnum {

    /**
     * 请求处理失败
     */
    FAILED(0, "请求处理失败", "Request failed."),
    /**
     * 请求处理成功
     */
    SUCCESS(1, "请求处理成功", "Request success."),

    /**
     * [1000,1999] 安全认证状态码
     */
    TOKEN_CORRECT(1000, "令牌合法", "The token is correct."),
    TOKEN_EMPTY(1001, "令牌为空", "The token is empty."),
    TOKEN_EXPIRED(1002, "令牌已过期", "The token is expired."),
    TOKEN_INVALID(1003, "无效令牌", "The token is invalid."),
    ENCRYPT_ALGORITHM_ERROR(1103, "加解密算法错误", "The encrypt algorithm is error."),
    ENCRYPT_INVALID_KEY(1104, "非法密钥", "The secret key is invalid."),
    ENCRYPT_ERROR(1105, "加密错误", "Encrypt content error."),
    DECRYPT_ERROR(1104, "解密错误", "Decrypt content error."),


    /**
     * [2000,2999] 请求接口格式校验状态码
     */
    HEADER_INVALID(2001, "非法请求头", "The request header is invalid."),


    /**
     * [3000,3999] 其他状态码
     */
    BUSINESS_ERROR(3000, "请求出错，请重试", "Request failed, retry please."),
    DATA_NOT_FOUND(3001, "没有更多数据", "No Data Found."),
    INTERNAL_ERROR(5000, "服务器出问题了，请稍后重试", "Something on server went wrong, please retry later.");


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
