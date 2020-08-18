package indi.ikun.spring.basejava.codequality.chapter08;

import java.util.zip.DataFormatException;

/**
 * 113.不要在finally块中处理返回值
 *
 * 不要在finally代码块中出现return语句
 *
 * 在项目中绝对不能在finally代码块中出现return语句，
 * 这是因为这种处理方式非常容易产生“误解”，会严重误导开发者
 *
 * finally是用来做异常的收尾处理的，
 * 一旦加上了return语句就会让程序的复杂度徒然提升，
 * 而且会产生一些隐蔽性非常高的错误。
 * 与return语句相似，
 * System.exit(0)
 * Runtime.getRuntime().exit(0)
 * 出现在异常代码块中也会产生非常多的错误假象，
 * 增加代码的复杂性
 *
 */
public class Ei {
    public static void main(String[] args) {

        try {
            System.err.println(doStuff(-1));//-1
            System.err.println(doStuff(100));//-1
        }catch (Exception e){
            System.err.println("这里永远都不会到达");
        }
        System.err.println(doStuff1());//1
        System.err.println(doStuff2().getName());//1
    }
    /**
     * 该方法抛出受检异常
     * finally代码块中加入了return语句，而这会导致出现以下两个问题：
     * 1）覆盖了try代码块中的return返回值
     *      当执行doStuff(-1)时，doStuff方法产生了DataFormatException异常，
     *      catch块在捕捉此异常后直接抛出，
     *      之后代码执行到finally代码块，就会重置返回值，结果就是-1了，
     *      也就是出现了先返回，再执行finally，再重置返回值的情况
     * 2）屏蔽异常
     *      异常线程在监视到有异常发生时，就会登记当前的异常类型为DataFormatException，
     *      但是当执行器执行finally代码块时，则会重新为doStuff方法赋值，
     *      也就是告诉调用者：“该方法执行正确，没有产生异常，返回值是1”，
     *      于是乎，异常神奇的消失了
     */
    public static int doStuff(int _p) throws Exception{
        try{
            if(_p<0){
                throw new DataFormatException("数据格式错误");
            }else {
                return  _p;
            }
        }catch (Exception e){
            throw e;
        }finally {
            //永远不会抛出异常
            return -1;
        }
    }

    /**
     * 该方法的返回值永远是1，而不会是-1或0（为什么不会执行到“return0”呢？
     * 原因是finally执行完毕后该方法已经有返回值了，后续代码就不会再执行了），
     * 这都是源于异常代码块的处理方式，
     * 在代码中加上try代码块就标志着运行时会有一个Throwable线程监视着该方法的运行，
     * 若出现异常，则交由异常逻辑处理。
     * 方法是在栈内存中运行的，
     * 并且会按照“先进后出”的原则执行，
     * main方法调用了doStuff方法，
     * 则main方法在下层，
     * doStuff在上层，
     * 当doStuff方法执行完“return a”时，
     * 此方法的返回值已经确定是int类型1（a变量的值，注意基本类型都是值拷贝，而不是引用），
     * 此后finally代码块再修改a的值已经与doStuff返回者没有任何关系了，
     * 因此该方法永远都会返回1
     */
    public static int doStuff1(){
        int a=1;
        try{
            return a;
        }catch (Exception e){
        }finally {
           a=-1;
        }
        return a;
    }

    public static PersonEi doStuff2(){
        PersonEi p=new PersonEi("张");
        try{
            return p;
        }catch (Exception e){

        }finally {
            p.setName("王");
        }
        p.setName("李");
        return p;
    }

}
class PersonEi{
    private String name;

    public PersonEi(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}