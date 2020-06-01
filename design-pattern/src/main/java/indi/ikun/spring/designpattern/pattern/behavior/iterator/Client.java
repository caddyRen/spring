package indi.ikun.spring.designpattern.pattern.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Iterator pattern 迭代器模式
 *
 * @author caddy
 */
public class Client {

    public static void main(String[] args) {
        List<College> colleges = new ArrayList<>();
        Computer computer = new Computer();
        Info info = new Info();

        colleges.add(computer);
        colleges.add(info);

        OutPutImpl outPut = new OutPutImpl(colleges);
        outPut.printCollege();


    }
}
