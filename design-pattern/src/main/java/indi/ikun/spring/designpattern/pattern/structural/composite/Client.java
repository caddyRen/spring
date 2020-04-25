package indi.ikun.spring.designpattern.pattern.structural.composite;

/**
 *
 */
public class Client {

    public static void main(String[] args) {
        First first = new First("first", "顶级组织");


        Second second1 = new Second("second1", "二级1");
        Second second2 = new Second("second2", "二级2");

        Last last1 = new Last("last1", "叶子1");
        Last last2 = new Last("last2", "叶子2");
        first.add(second1);
        first.add(second2);

        second1.add(last1);
        second1.add(last2);
        first.print();
        second1.print();
        second2.print();
        last1.print();
        last2.print();


    }


}