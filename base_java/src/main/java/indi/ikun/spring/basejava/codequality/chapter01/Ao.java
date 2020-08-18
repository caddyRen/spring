package indi.ikun.spring.basejava.codequality.chapter01;

/**
 * 15.break万万不可忘
 */
public class Ao {
    public static void main(String[] args) {
        System.err.println("2-"+toChineseNumber(2));
    }


    public static String toChineseNumber(int n){
        String chineseNumber="";
        //错误示范
        switch (n){
            case 0:chineseNumber="零";
            case 1:chineseNumber="壹";
            case 2:chineseNumber="贰";
            case 3:chineseNumber="叁";
            case 4:chineseNumber="肆";
            case 5:chineseNumber="伍";
            case 6:chineseNumber="陆";
            case 7:chineseNumber="柒";
            case 8:chineseNumber="捌";
            case 9:chineseNumber="玖";
        }
        //加上break示范
        switch (n){
            case 0:
            {
                chineseNumber="零";
                break;
            }
            case 1:{
                chineseNumber="壹";
                break;
            }
            case 2:{
                chineseNumber="贰";
                break;
            }
            case 3:{
                chineseNumber="叁";
                break;
            }
            case 4:{
                chineseNumber="肆";
                break;
            }
            case 5:{
                chineseNumber="伍";
                break;
            }
            case 6:{
                chineseNumber="陆";
                break;
            }
            case 7:{
                chineseNumber="柒";
                break;
            }
            case 8:{
                chineseNumber="捌";
                break;
            }
            case 9:{
                chineseNumber="玖";
                break;
            }
        }
        return chineseNumber;
    }

}