package indi.ikun.spring.proxy.bean.logging;

import lombok.*;

/**
 * @Author renqiankun
 * @Description //api调用记录类
 * @Date 2019-10-31 12:40
 * @Version 1.0
 * @UpdateBy
 **/
@Builder
@Getter
@Setter
@Data
@ToString
public class ApiLogging {

    private Long requestId;
    /**
     * 访问的URI
     */
    private String requestUri;
    /**
     * 客户端IP
     */
    private String requestClient;
    /**
     * 载体信息
     */
    private String requestPayload;
    /**
     * 用户信息
     */
    private String requestUser;
    /**
     * session信息
     */
    private String requestSession;
    /**
     * 头部信息
     */
    private String requestHeaders;
    /**
     * 请求状态(前后)
     */
    private String requestStatus;
    /**
     * 访问时间
     */
    private Long accessDate;

    /**
     * 所属服务名称
     */
    private String serverName;

    /**
     * 访问参数
     */
    private String requestParameters;


    @java.beans.ConstructorProperties({"requestId", "requestUri", "requestClient", "requestPayload", "requestUser", "requestSession", "requestHeaders", "requestStatus", "accessDate", "serverName", "requestParameters"})
    public ApiLogging(Long requestId, String requestUri, String requestClient, String requestPayload, String requestUser, String requestSession, String requestHeaders, String requestStatus, Long accessDate, String serverName, String requestParameters) {
        this.requestId = requestId;
        this.requestUri = requestUri;
        this.requestClient = requestClient;
        this.requestPayload = requestPayload;
        this.requestUser = requestUser;
        this.requestSession = requestSession;
        this.requestHeaders = requestHeaders;
        this.requestStatus = requestStatus;
        this.accessDate = accessDate;
        this.serverName = serverName;
        this.requestParameters = requestParameters;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ApiLogging)) {
            return false;
        }
        final ApiLogging other = (ApiLogging) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        final Object this$requestId = this.getRequestId();
        final Object other$requestId = other.getRequestId();
        if (this$requestId == null ? other$requestId != null : !this$requestId.equals(other$requestId)) {
            return false;
        }
        final Object this$requestUri = this.getRequestUri();
        final Object other$requestUri = other.getRequestUri();
        if (this$requestUri == null ? other$requestUri != null : !this$requestUri.equals(other$requestUri)) {
            return false;
        }
        final Object this$requestClient = this.getRequestClient();
        final Object other$requestClient = other.getRequestClient();
        if (this$requestClient == null ? other$requestClient != null : !this$requestClient.equals(other$requestClient)) {
            return false;
        }
        final Object this$requestPayload = this.getRequestPayload();
        final Object other$requestPayload = other.getRequestPayload();
        if (this$requestPayload == null ? other$requestPayload != null : !this$requestPayload.equals(other$requestPayload)) {
            return false;
        }
        final Object this$requestUser = this.getRequestUser();
        final Object other$requestUser = other.getRequestUser();
        if (this$requestUser == null ? other$requestUser != null : !this$requestUser.equals(other$requestUser)) {
            return false;
        }
        final Object this$requestSession = this.getRequestSession();
        final Object other$requestSession = other.getRequestSession();
        if (this$requestSession == null ? other$requestSession != null : !this$requestSession.equals(other$requestSession)) {
            return false;
        }
        final Object this$requestHeaders = this.getRequestHeaders();
        final Object other$requestHeaders = other.getRequestHeaders();
        if (this$requestHeaders == null ? other$requestHeaders != null : !this$requestHeaders.equals(other$requestHeaders)) {
            return false;
        }
        final Object this$requestStatus = this.getRequestStatus();
        final Object other$requestStatus = other.getRequestStatus();
        if (this$requestStatus == null ? other$requestStatus != null : !this$requestStatus.equals(other$requestStatus)) {
            return false;
        }
        final Object this$accessDate = this.getAccessDate();
        final Object other$accessDate = other.getAccessDate();
        if (this$accessDate == null ? other$accessDate != null : !this$accessDate.equals(other$accessDate)) {
            return false;
        }
        final Object this$serverName = this.getServerName();
        final Object other$serverName = other.getServerName();
        if (this$serverName == null ? other$serverName != null : !this$serverName.equals(other$serverName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $requestId = this.getRequestId();
        result = result * PRIME + ($requestId == null ? 43 : $requestId.hashCode());
        final Object $requestUri = this.getRequestUri();
        result = result * PRIME + ($requestUri == null ? 43 : $requestUri.hashCode());
        final Object $requestClient = this.getRequestClient();
        result = result * PRIME + ($requestClient == null ? 43 : $requestClient.hashCode());
        final Object $requestPayload = this.getRequestPayload();
        result = result * PRIME + ($requestPayload == null ? 43 : $requestPayload.hashCode());
        final Object $requestUser = this.getRequestUser();
        result = result * PRIME + ($requestUser == null ? 43 : $requestUser.hashCode());
        final Object $requestSession = this.getRequestSession();
        result = result * PRIME + ($requestSession == null ? 43 : $requestSession.hashCode());
        final Object $requestHeaders = this.getRequestHeaders();
        result = result * PRIME + ($requestHeaders == null ? 43 : $requestHeaders.hashCode());
        final Object $requestStatus = this.getRequestStatus();
        result = result * PRIME + ($requestStatus == null ? 43 : $requestStatus.hashCode());
        final Object $accessDate = this.getAccessDate();
        result = result * PRIME + ($accessDate == null ? 43 : $accessDate.hashCode());
        final Object $serverName = this.getServerName();
        result = result * PRIME + ($serverName == null ? 43 : $serverName.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ApiLogging;
    }


}
