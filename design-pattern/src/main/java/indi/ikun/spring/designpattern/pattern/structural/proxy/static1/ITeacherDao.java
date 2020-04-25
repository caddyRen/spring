package indi.ikun.spring.designpattern.pattern.structural.proxy.static1;


/**
 * 统一接口
 */
public interface ITeacherDao {

    void teach();
}

/**
 * 目标对象，被代理对象
 */
class Teacher implements ITeacherDao{

    @Override
    public void teach() {
        System.err.println("Teacher 正在工作");
    }
}

/**
 * 代理对象，client使用此对象调用被代理对象的teach方法
 */
class TeacherProxy implements ITeacherDao{

    //代理目标,根据依赖倒转原则，不要直接new Teacher，使用构造器传入Teacher
    private ITeacherDao iTeacherDao;

    public TeacherProxy(ITeacherDao iTeacherDao) {
        this.iTeacherDao = iTeacherDao;
    }

    @Override
    public void teach() {
        System.err.println("TeacherProxy 前");
        iTeacherDao.teach();
        System.err.println("TeacherProxy 后");
    }
}

