package indi.ikun.spring.designpattern.ocp;

/**
 * @Description: 开闭原则
 * @Author caddy
 * @date 2020-02-05 16:44:25
 * @version 1.0
 * 使用方 GraphicEditor
 * 提供方 Shape
 * 设计要求:提供方开启扩展，使用方关闭修改
 * 如果新增一个三角形，提供广可以扩展，但是使用方也要作很大修改，不符合开闭原则，
 */
public class Ocp {

    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Retangle());
    }
}
class GraphicEditor{
    public void drawShape(Shape shape){
        if(shape.m_type==1){
            drawCircle();
        }else if (shape.m_type==2){
            drawRetangle();
        }
    }

    public void drawCircle(){
        System.err.println("圆");
    }

    public void drawRetangle(){
        System.err.println("矩形");
    }

}

class Shape{
    int m_type;
}

class Retangle extends Shape{
    Retangle(){
        super.m_type=2;
    }
}
class Circle extends Shape{
    Circle(){
        super.m_type=1;
    }
}