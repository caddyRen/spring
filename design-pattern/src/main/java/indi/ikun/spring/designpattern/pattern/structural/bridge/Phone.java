package indi.ikun.spring.designpattern.pattern.structural.bridge;

/**
 * @Description: 抽出实现类
 * @Author caddy
 * @date 2020-02-12 13:51:50
 * @version 1.0
 */
public class Phone {
    private Brand brand;

    public Phone(Brand brand) {
        super();
        this.brand = brand;
    }

    protected void open(){
        this.brand.open();
    }

    protected void close(){
        this.brand.close();
    }

    protected void call(){
        this.brand.call();
    }
}

/**
 * @Description: 具体实现类---平板手机
 * @Author caddy
 * @date 2020-02-12 14:02:17
 * @version 1.0
 */
class UpRightPhone extends Phone{

    public UpRightPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open(){
        super.open();
        System.err.println("UpRightPhone");
    }

    @Override
    protected void close() {
        super.close();
        System.err.println("UpRightPhone");
    }

    @Override
    protected void call() {
        super.call();
        System.err.println("UpRightPhone");
    }
}


/**
 * @Description: 具体实现类  折叠手机  扩展手机种类的时候很方便
 * @Author caddy
 * @date 2020-02-12 14:02:17
 * @version 1.0
 */
class FoldedPhone extends Phone{

    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected  void open(){
        super.open();
        System.err.println("FoldedPhone");
    }

    @Override
    protected void close() {
        super.close();
        System.err.println("FoldedPhone");
    }

    @Override
    protected void call() {
        super.call();
        System.err.println("FoldedPhone");
    }
}

