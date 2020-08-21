package indi.ikun.spring.designpattern.demeter.improve;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 按照迪米特法则 改进
 * @Author caddy
 * @date 2020-02-05 17:16:50
 * @version 1.0
 */
public class Demeter {

    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmplayee(new CollegeManager());
    }
}

class Employee{
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class CollegeEmployee{
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class CollegeManager{

    public List<CollegeEmployee> getAllEmployee(){
        List<CollegeEmployee> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            CollegeEmployee employee=new CollegeEmployee();
            employee.setId(i);
            list.add(employee);
        }
        return list;
    }

    public void print(){
        //CollegeEmployee  违背迪米特法则
        List<CollegeEmployee> list1=this.getAllEmployee();
        for (CollegeEmployee e:list1
        ) {
            System.err.println(e.getId());
        }
    }

}

/**
 * @Author caddy
 * @date 2020-02-05 17:31:04
 * @version 1.0
 * 直接朋友
 * Employee
 * CollegeManager
 * 陌生类
 * CollegeEmployee
 */
class SchoolManager{
    public List<Employee> getAllEmployee(){
        List<Employee> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            Employee employee=new Employee();
            employee.setId(i);
            list.add(employee);
        }
        return list;
    }

    /**
     * @Description: CollegeEmployee 非朋友关系 可以改进
     * @Author caddy
     * @date 2020-02-05 17:29:43
     * @version 1.0

     */
    void printAllEmplayee(CollegeManager c){
        //CollegeManager自己处理输出逻辑，只暴露结果方法出来
        c.print();
        System.err.println("------");
        List<Employee> list2=this.getAllEmployee();
        for (Employee e:list2
             ) {
            System.err.println(e.getId());
        }
    }
}
/**
 * 1.整理h3接入的文档和代码
 * 2.埋点数据
 * 3.生成word文档
 */

