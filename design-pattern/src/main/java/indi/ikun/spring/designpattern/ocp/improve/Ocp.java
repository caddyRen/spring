package indi.ikun.spring.designpattern.ocp.improve;

/**
 * @Description: 符合开闭原则，依赖倒转的demo代码也类似
 * @Author caddy
 * @date 2020-02-05 16:54:17
 * @version 1.0
 */
public class Ocp {

    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Retangle());
        graphicEditor.drawShape(new Triangle());
    }
}
/**
 * @Description: 使用方关闭修改
 * @Author caddy
 * @date 2020-02-05 16:57:54
 * @version 1.0
 */
class GraphicEditor{
    public void drawShape(Shape shape){
        shape.draw();
    }
}

/**
 * @Description: 基类，
 * @Author caddy
 * @date 2020-02-05 16:58:11
 * @version 1.0
 */
abstract class Shape{
    int m_type;
    public abstract void draw();
}


/**
 * @Description: 提供方开启修改
 * @Author caddy
 * @date 2020-02-05 16:58:48
 * @version 1.0
 */
class Retangle extends Shape {
    Retangle(){
        super.m_type=2;
    }
    @Override
    public void draw(){
        System.err.println("矩形");
    }
}

class Circle extends Shape {
    Circle(){
        super.m_type=1;
    }
    @Override
    public void draw(){
        System.err.println("圆");
    }
}
class Triangle extends Shape {
    Triangle(){
        super.m_type=1;
    }
    @Override
    public void draw(){
        System.err.println("三角形");
    }
}