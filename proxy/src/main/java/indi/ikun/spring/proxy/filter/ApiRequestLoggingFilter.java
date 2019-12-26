package indi.ikun.spring.proxy.filter;

import com.alibaba.fastjson.JSONObject;
import indi.ikun.spring.proxy.bean.logging.ApiLogging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * @Author renqiankun
 * @Description //api调用记录
 * @Date 2019-10-31 12:39
 * @Version 1.0
 * @UpdateBy
 **/
public class ApiRequestLoggingFilter extends CommonsRequestLoggingFilter {

    private static final Logger log = LoggerFactory.getLogger(ApiRequestLoggingFilter.class);

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        super.beforeRequest(request, message);
        ApiLogging apiLogging = createBaseApiLogging(request, "BEFORE");

    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        super.afterRequest(request, message);
        ApiLogging apiLogging = createBaseApiLogging(request, "AFTER");

    }

    /**
     * 创建基础ApiLogging对象
     *
     * @param request       请求信息
     * @param requestStatus 请求状态
     *
     * @return ApiLogging
     */
    private ApiLogging createBaseApiLogging(HttpServletRequest request, String requestStatus) {
        String uri = request.getRequestURI();
        if (request.getQueryString() != null) {
            uri = "?" + request.getQueryString();
        }

        String client = request.getRemoteAddr();
        String sessionId = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            sessionId = session.getId();
        }

        String user = request.getRemoteUser();

        String headers = String.valueOf(new ServletServerHttpRequest(request).getHeaders());

        String payload = null;

        ContentCachingRequestWrapper wrapper =
            WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, getMaxPayloadLength());
                try {
                    payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    payload = "[unknown]";
                }
            }
        }
        String parameters = JSONObject.toJSONString(request.getParameterMap());
        String appName=request.getParameter("appName");
        ApiLogging apiLogging = ApiLogging.builder()
            .requestUri(uri)
            .requestClient(client)
            .requestSession(sessionId)
            .requestUser(user)
            .requestHeaders(headers)
            .requestParameters(parameters)
            .requestPayload(payload)
            .requestStatus(requestStatus)
            .accessDate(System.currentTimeMillis())
            .serverName(appName)
            .build();

        if (log.isDebugEnabled()) {
            log.debug("==> ApiLogging: {}", apiLogging.toString());
        }

        return apiLogging;
    }
}
