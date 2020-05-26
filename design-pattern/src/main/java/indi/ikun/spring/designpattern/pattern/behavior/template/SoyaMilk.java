package indi.ikun.spring.designpattern.pattern.behavior.template;

/**
 * 抽象类,豆浆
 */
public abstract class SoyaMilk {

    /**
     * 模板方法 final 修饰,防止子类重写模板方法
     */
    final void make(){
        select();
        if(hook()){
            addCondiments();
        }
        soak();
        beat();
    }

    /**
     * 选材
     */
    private void select(){
        System.err.println("选黄豆");
    }

    /**
     * 添加配料
     */
    abstract void addCondiments();

    /**
     * 浸泡
     */
    private void soak(){
        System.err.println("浸泡");
    }

    /**
     * 打豆浆
     */
    private void beat(){
        System.err.println("放入豆浆机打碎");
    }

    /**
     * 模板精髓 -----钩子
     * @return
     */
    public boolean hook(){
        return true;
    }

}


/**
 * 花生豆浆
 */
class  PeanutSoyaMilk extends SoyaMilk{
    @Override
    void addCondiments() {
        System.err.println("加入花生");
    }
}

/**
 * 红豆豆浆
 */
class  RedBeanSoyaMilk extends SoyaMilk{
    @Override
    void addCondiments() {
        System.err.println("加入红豆");
    }
}
/**
 * 纯豆浆 钩子
 */
class  PureSoyaMilk extends SoyaMilk{

    @Override
    void addCondiments() {
        //空方法
    }

    @Override
    public boolean hook() {
        return false;
    }
}





