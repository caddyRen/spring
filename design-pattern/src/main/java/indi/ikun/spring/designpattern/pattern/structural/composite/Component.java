package indi.ikun.spring.designpattern.pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共类
 */
public abstract class Component {

    public String name;
    public String desc;

    public Component(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 叶子节点不需要实现，所以不设置成抽象
     * @param component
     */
    protected void add(Component component){
        //        默认实现
        throw new UnsupportedOperationException();
    }

    /**
     * 叶子节点不需要实现 所以不设置成抽象
     * @param component
     */
    protected void remove(Component component){
        //        默认实现
        throw new UnsupportedOperationException();

    }

    /**
     * 所有节点都需要的方法，所以设置成抽象
     */
    protected abstract void print();
}


/**
 * 管理Second
 */
class First extends Component{

    public First(String name, String desc) {
        super(name, desc);
    }

    //components 内部存放的是Second
    List<Component> components=new ArrayList<Component>();

    @Override
    protected void add(Component component) {
        components.add(component);
    }

    @Override
    protected void remove(Component component) {
        components.remove(component);
    }

    @Override
    protected void print() {
        System.err.println("----"+getName()+"-----"+getDesc());
        for (Component component:components){
            System.err.println(component.getName()+"==="+component.getDesc());
        }
    }
}


class Second extends Component{

    public Second(String name, String desc) {
        super(name, desc);
    }

    //components 内部存放的是Last
    List<Component> components=new ArrayList<Component>();

    @Override
    protected void add(Component component) {
        components.add(component);
    }

    @Override
    protected void remove(Component component) {
        components.remove(component);
    }

    @Override
    protected void print() {
        System.err.println("----"+getName()+"-----"+getDesc());
        for (Component component:components){
            System.err.println(component.getName()+"==="+component.getDesc());
        }
    }
}

/**
 * 叶子节点
 */
class Last extends Component{


    public Last(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void print() {
        System.err.println("----"+getName()+"-----"+getDesc());
    }
}
