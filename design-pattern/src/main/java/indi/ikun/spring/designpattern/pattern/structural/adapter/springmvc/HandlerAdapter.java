package indi.ikun.spring.designpattern.pattern.structural.adapter.springmvc;

/**
 * @Description: 适配器接口
 * @Author caddy
 * @date 2020-02-12 10:53:52
 * @version 1.0
 */
public interface HandlerAdapter {


    public boolean supports(Object handler);

    public void handle(Object handler);

}


/**
 * 多种适配器
 */
class SimpleHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof SimpleController;
    }

    @Override
    public void handle(Object handler) {
        ((SimpleController)handler).doSimpleHandler();
    }
}

class HttpHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof HttpController;
    }

    @Override
    public void handle(Object handler) {
        ((HttpController)handler).doHttpHandler();
    }
}

class AnnotationHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof AnnotationController;
    }

    @Override
    public void handle(Object handler) {
        ((AnnotationController)handler).doAnnotationHandler();
    }
}



