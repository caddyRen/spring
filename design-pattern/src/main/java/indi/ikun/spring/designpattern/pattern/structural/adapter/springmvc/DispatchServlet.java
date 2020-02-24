package indi.ikun.spring.designpattern.pattern.structural.adapter.springmvc;

import java.util.ArrayList;
import java.util.List;

public class DispatchServlet {

    public static List<HandlerAdapter> handlerAdapters=new ArrayList<>();

    public DispatchServlet() {
        handlerAdapters.add(new AnnotationHandlerAdapter());
        handlerAdapters.add(new HttpHandlerAdapter());
        handlerAdapters.add(new SimpleHandlerAdapter());
    }

    public void doDispatch(){
        //此处模拟SpringMVC从request取handler对象
        //适配器可以获取到希望的Controller
//        HttpController controller=new HttpController();
//        SimpleController controller=new SimpleController();
        AnnotationController controller=new AnnotationController();
        //获取对应适配器：Controller变化，适配器也随之变化获取对应的Adapter
        HandlerAdapter adapter=getHandler(controller);
        //通过适配器执行对应的controller对应的方法
        adapter.handle(controller);
    }

    public HandlerAdapter getHandler(Controller controller){
        //遍历 根据得到的controller，返回对应的适配器
        for(HandlerAdapter adapter: handlerAdapters){
            if(adapter.supports(controller)){
                return adapter;
            }
        }
        return null;
    }


    public static void main(String[] args) {

        new DispatchServlet().doDispatch();
    }
}
