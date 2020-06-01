package indi.ikun.spring.designpattern.inversion;


/**
 * @Description: 依赖关系传递
 * @Author caddy
 * @date 2020-02-04 16:29:03
 * @version 1.0
 */
public class Dependency {

    public static void main(String[] args) {
        //接口传递实现依赖
        OpenAndClose o=new OpenAndClose();
        o.open(new TV());
        OpenAndClose2 o2=new OpenAndClose2(new TV());
        o2.open();
        OpenAndClose3 o3=new OpenAndClose3();
        o3.setItv(new TV());
        o3.open();
    }
}


interface IOpenAndClose{
    void open(ITV itv);
}

interface ITV{
    void play();
}

class TV implements ITV{
    @Override
    public void play() {
        System.err.println("现在播放海面宝宝");
    }
}

/**
 * @Description: 接口传递实现依赖
 * @Author caddy
 * @date 2020-02-04 16:28:55
 * @version 1.0
 */
class OpenAndClose implements IOpenAndClose{
    @Override
    public void open(ITV itv) {
        itv.play();
    }
}

/**
 * @Description: 构造方法依赖传递
 * @Author caddy
 * @date 2020-02-04 16:28:55
 * @version 1.0
 */
class OpenAndClose2{
    ITV itv;
    public OpenAndClose2(ITV itv){
        this.itv=itv;
    }
    public void open(){
        itv.play();
    }
}

/**
 * @Description: setter方法依赖传递
 * @Author caddy
 * @date 2020-02-04 16:28:55
 * @version 1.0
 */
class OpenAndClose3{
    ITV itv;
    public void setItv(ITV itv) {
        this.itv = itv;
    }
    public void open(){
        itv.play();
    }
}

