package indi.ikun.spring.demo.config.cyclicdpns;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName StudentA
 * @Author caddyR
 * @Date 2019-09-12 09:22
 * @Version 1.0
 **/
public class StudentC{
    private StudentA studentA;
    @Autowired
    public void setStudentA(StudentA studentA){
        this.studentA=studentA;
    }

    public StudentC(){

    }

    public void print(){
        System.err.println("C"+studentA);
    }
}
