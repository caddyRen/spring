package com.mumu.springcloud.demo.config.cyclicdpns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName StudentA
 * @Description TODO
 * @Author caddyR
 * @Date 2019-09-12 09:22
 * @Version 1.0
 **/
public class StudentA{
    private StudentB studentB;


    @Autowired
    public void setStudentB(StudentB studentB){
        this.studentB=studentB;
    }

    public StudentA(){

    }
//    public StudentB getStudentB(){
//        return studentB;
//    }

    public void print(){
        System.err.println("A"+studentB);
    }
}
