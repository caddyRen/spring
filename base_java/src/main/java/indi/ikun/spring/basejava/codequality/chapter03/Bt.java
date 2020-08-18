package indi.ikun.spring.basejava.codequality.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 46.equals应该考虑null值情景
 */
public class Bt {
    public static void main(String[] args) {
        PersonBt p1 = new PersonBt("张三");
        PersonBt p2 = new PersonBt(null);
        List<PersonBt> l=new ArrayList<>();
        l.add(p1);
        l.add(p2);
        System.err.println(l.contains(p1));
        System.err.println(l.contains(p2));
    }
}
class PersonBt{
    private String name;

    public PersonBt(String _name){
        name=_name;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof PersonBt){
            PersonBt p=(PersonBt)obj;
            //name=null时空指针异常
//            return name.equalsIgnoreCase(p.getName());

            //优化
            if(p.getName()==null||name==null){
                return false;
            }else
                return name.equalsIgnoreCase(p.getName());

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