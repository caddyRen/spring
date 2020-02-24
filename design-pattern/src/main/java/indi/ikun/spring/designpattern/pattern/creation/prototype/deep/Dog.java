package indi.ikun.spring.designpattern.pattern.creation.prototype.deep;

import java.io.Serializable;

public class Dog implements Serializable,Cloneable {
    private static final long serailVersionUID=1L;

    String Name;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public Dog(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
