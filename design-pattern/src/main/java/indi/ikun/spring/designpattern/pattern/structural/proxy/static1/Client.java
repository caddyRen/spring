package indi.ikun.spring.designpattern.pattern.structural.proxy.static1;

public class Client {

    public static void main(String[] args) {

        TeacherProxy teacherProxy=new TeacherProxy(new Teacher());
        teacherProxy.teach();

    }

}
