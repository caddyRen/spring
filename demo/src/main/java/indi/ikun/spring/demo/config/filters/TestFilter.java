package indi.ikun.spring.demo.config.filters;


import com.google.gson.Gson;
import indi.ikun.spring.demo.config.interceptors.BodyReaderHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author renqiankun
 * @Description
 *              servlet调用之前截获，可以检查request，修改request数据
 *              servlet调用之后截获，可以修改response头和response数据
 * @Date 2018/5/2
 * @Modified By
 */
@Slf4j
public class TestFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("过滤器 is running...");
        ServletRequest servletRequest=null;
        if(request instanceof HttpServletRequest){
            servletRequest=new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if(servletRequest==null){
            chain.doFilter(request,response);
        }else {
            log.error(getRequestParameter((HttpServletRequest) request));
            chain.doFilter(servletRequest,response);
        }
        //log.error(getRequestHeaders(httpServletRequest));
//        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("过滤器 destroy...");
    }
    /**
     * 获取请求头
     */
    private String getRequestHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return objectToString(map);
    }


    /**
     * 获取返回头
     */
    private String getResponseHeaders(HttpServletResponse response) {
        Map<String, String> map = new HashMap<String, String>();
        Collection<String> headerNames = response.getHeaderNames();
        for (Iterator iter = headerNames.iterator(); iter.hasNext(); ) {
            String key = (String) iter.next();
            String value = response.getHeader(key);
            map.put(key, value);
        }
        return objectToString(map);
    }


    /**
     * 获取请求参数
     */
    private String getRequestParameter(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String key = (String) paramNames.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }
        if(map.size()>0){

            return objectToString(map);
        }
        else{
            BufferedReader reader=null;
            String wholeStr="";
            try {
                reader=new BufferedReader(new InputStreamReader(request.getInputStream()));
                String string="";
                //一行一行读取body体了里面的内容
                while((string=reader.readLine())!=null){
                    wholeStr+=string;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return wholeStr;
            }

        }
    }

    private String objectToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return new Gson().toJson(obj);
    }
}
