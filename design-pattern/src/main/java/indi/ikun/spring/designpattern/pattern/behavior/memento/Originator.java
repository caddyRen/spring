package indi.ikun.spring.designpattern.pattern.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要保存状态的角色
 */
public class Originator {

    private String state;//状态信息

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 保存一个状态对象Memento
     *
     * @return
     */
    public Memento saveStateMement() {
        return new Memento(state);
    }

    /**
     * 恢复状态
     * @return
     */
    public void getStateMement(Memento memento) {
       setState(memento.getState());
    }

}

/**
 * 记录状态
 */
class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void getStateMemento(Memento memento) {
        state =memento.getState();
    }
}


/**
 * 守护者,保存多个备忘录对象
 *
 * 多种备忘可以用HashMap集合保存
 */
class Caretaker{
    private List<Memento> mementos=new ArrayList<>();

    public void add(Memento memento){
        mementos.add(memento);
    }

    /**
     * 获取指定备忘录
     * @param index
     * @return
     */
    public Memento get(int index){
        return mementos.get(index);
    }
}




