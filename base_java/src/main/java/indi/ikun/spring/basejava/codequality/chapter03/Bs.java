package indi.ikun.spring.basejava.codequality.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 45.覆写equals方法时不要识别不出自己
 */
public class Bs {
    public static void main(String[] args) {
        PersonBs p1 = new PersonBs("张三");
        PersonBs p2 = new PersonBs("张三 ");
        List<PersonBs> l=new ArrayList<>();
        l.add(p1);
        l.add(p2);
        System.err.println(l.contains(p1));
        System.err.println(l.contains(p2));
    }
}
class PersonBs{
    private String name;

    public PersonBs(String _name){
        name=_name;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof PersonBs){
            PersonBs p=(PersonBs)obj;
            //违反了equals的自反性原则：对于任何非空引用x，x.equals(x)应该返回true，
            //解决也非常容易，只要把trim()去掉即可，注意解决的只是当前问题，该equals方法还存在其他问题
            return name.equalsIgnoreCase(p.getName().trim());
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
