package indi.ikun.spring.basejava.codequality.chapter01;


/**
 * 8.不要让旧语法困扰你
 * Java中虽然没有了goto关键字，但是扩展了break和continue关键字，它们的后面都可以加上标号做跳转，完全实现了goto功能
 * : 标号，直接跳出标记的循环
 * 现在使用break，continue替代,不过跳出多重循环可以使用标号
 */
public class Ah {

    public static void main(String[] args) {
        int fee=200;
        saveDefault:save(fee);
        saveDefault:saveNow(fee);
    }


    private static void save(int fee) {
        loop1:
        for (int i=0;i<3;i++){
            loop2:
            for(int j=0;j<2;j++){
                fee++;
                if(i==1){
                    System.err.println("i="+i);
                    System.err.println("j="+j);
                    break loop1;
                }
                if(i==0){
                    System.err.println("i="+i);
                    System.err.println("j="+j);
                    break loop2;
                }
            }
        }
        System.err.println(fee);
    }
    private static void saveNow(int fee) {
        for (int i=0;i<3;i++){
            for(int j=0;j<2;j++){
                fee++;
                if(i==1){
                    System.err.println("i="+i);
                    System.err.println("j="+j);
                    break;
                }
                if(i==0){
                    System.err.println("i="+i);
                    System.err.println("j="+j);
                    break;
                }
            }
        }
        System.err.println(fee);
    }

}