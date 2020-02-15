package indi.ikun.spring.designpattern.pattern.structural.adapter.springmvc;

/**
 * @Description: 多种Controller
 * @Author caddy
 * @date 2020-02-12 10:57:38
 * @version 1.0
 */
public interface Controller {

}

class HttpController implements Controller{
    public void doHttpHandler(){
        System.err.println("doHttpHandler");
    }
}

class SimpleController implements Controller{
    public void doSimpleHandler(){
        System.err.println("doSimpleHandler");
    }
}

class AnnotationController implements Controller{
    public void doAnnotationHandler(){
        System.err.println("doAnnotationHandler");
    }
}