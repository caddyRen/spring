package indi.ikun.spring.designpattern.pattern.behavior.responsibility;

/**
 * Chain of Responsibility Pattern
 *
 * 责任链模式
 *
 * 为请求创建一个接收者对象的链
 * 对请求的发送者和接收者进行解耦
 *
 * 责任链模式 通常每个接收者都包含对另一个接收者的引用
 * 如果一个对象不能处理该请求,那么它会把相同的请求传给下一个接收者,依次类推
 *
 */
public class Client {

    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 20000.0f, 100);

        Department department = new Department("教学主任");
        College college = new College("院长");
        ViceSchoolMaster viceSchoolMaster = new ViceSchoolMaster("副校长");
        SchoolMaster schoolMaster = new SchoolMaster("校长");

        department.setApprovr(college);
        college.setApprovr(viceSchoolMaster);
        viceSchoolMaster.setApprovr(schoolMaster);
        schoolMaster.setApprovr(department);

        department.processRequest(purchaseRequest);
        college.processRequest(purchaseRequest);
        viceSchoolMaster.processRequest(purchaseRequest);
        schoolMaster.processRequest(purchaseRequest);



    }
}
