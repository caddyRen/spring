package indi.ikun.spring.demo.config.cyclicdpns;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName StudentA
 * @Description TODO
 * @Author caddyR
 * @Date 2019-09-12 09:22
 * @Version 1.0
 **/
public class StudentB{

    private StudentC studentC;

    @Autowired
    public void setStudentC(StudentC studentC){
        this.studentC=studentC;
    }
    public StudentB(){

    }

    public void print(){
        System.err.println("B"+studentC);
    }
}
