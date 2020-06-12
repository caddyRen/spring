package indi.ikun.spring.designpattern.pattern.behavior.responsibility;

public abstract class Approver {

    Approver approvr;
    String name;

    public Approver(String name) {
        this.name = name;
    }

    /**
     * 下一个处理者
     * @param approvr
     */
    public void setApprovr(Approver approvr) {
        this.approvr = approvr;
    }

    abstract void processRequest(PurchaseRequest purchaseRequest);
}

/**
 * 教学主任 审批<=5000
 */
class Department extends Approver{
    public Department(String name) {
        super(name);
    }

    @Override
    void processRequest(PurchaseRequest purchaseRequest) {
            if(purchaseRequest.getPrice()<=5000){
                System.err.println(name+"处理了"+purchaseRequest.getId());
            }else {
                System.err.println(name+"--->"+approvr.name);
                approvr.processRequest(purchaseRequest);
            }
    }
}

/**
 * 院长审批 <=10000
 */
class College extends Approver{
    public College(String name) {
        super(name);
    }

    @Override
    void processRequest(PurchaseRequest purchaseRequest) {
        if(purchaseRequest.getPrice()<=10000){
            System.err.println(name+"处理了"+purchaseRequest.getId());
        }else {
            System.err.println(name+"--->"+approvr.name);
            approvr.processRequest(purchaseRequest);
        }
    }
}

/**
 * 副校长 <=30000
 */
class ViceSchoolMaster extends Approver{
    public ViceSchoolMaster(String name) {
        super(name);
    }

    @Override
    void processRequest(PurchaseRequest purchaseRequest) {
        if(purchaseRequest.getPrice()<=30000){
            System.err.println(name+"处理了"+purchaseRequest.getId());
        }else {
            System.err.println(name+"--->"+approvr.name);
            approvr.processRequest(purchaseRequest);
        }
    }
}

/**
 * 校长 >30000
 */
class SchoolMaster extends Approver{
    public SchoolMaster(String name) {
        super(name);
    }

    @Override
    void processRequest(PurchaseRequest purchaseRequest) {
        if(purchaseRequest.getPrice()>30000){
            System.err.println(name+"处理了"+purchaseRequest.getId());
        }else {
            System.err.println(name+"--->"+approvr.name);
            approvr.processRequest(purchaseRequest);
        }
    }
}