package indi.ikun.spring.demo.config.listeners;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author renqiankun
 * @Description 请求级别的监听器，每次请求都会触发事件，可以记录请求接口的调用记录，不过要获取接口调用结果，需要将结果放到request内
 * @Date  2018/5/10
 * @Modified By
 */
@Slf4j
public class MyServletRequestListerner implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        HttpServletRequest request=(HttpServletRequest) sre.getServletRequest();
        //HttpServletResponse response=(HttpServletResponse) sre.getServletRequest();
        //System.err.println(request.getRequestURI());//
        //System.err.println(request.getRequestURL());//



        log.info("请求结束，监听器销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("发生请求，监听开始");
    }
}
