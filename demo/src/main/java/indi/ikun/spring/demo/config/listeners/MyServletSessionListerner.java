package indi.ikun.spring.demo.config.listeners;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author renqiankun
 * @Description 会话监听器
 * @Date 16:40 2018/5/10
 * @Modified By
 */
@Slf4j
public class MyServletSessionListerner implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx=se.getSession().getServletContext();
        Integer numSession=(Integer)ctx.getAttribute("numSession");
        if(numSession==null ||"".equals(numSession)){
            numSession=new Integer(1);
        }else {
            numSession++;
        }
        ctx.setAttribute("numSession",numSession);
        log.info("session create");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx=se.getSession().getServletContext();
        Integer numSession=(Integer)ctx.getAttribute("numSession");
        if(numSession==null ||"".equals(numSession)){
            numSession=new Integer(0);
        }else{
            numSession--;
        }
        ctx.setAttribute("numSession",numSession);
        log.error("session destroy");
    }
}
