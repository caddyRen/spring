package indi.ikun.spring.designpattern.javabase.string;

import java.io.UnsupportedEncodingException;

/**
 * Java乱码  一般都是字符集不一致造成的
 */
public class UTFCode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str="中文是否乱码";
        //编辑器默认是UTF-8,此处使用GBK
        byte[] b=str.getBytes("GBK");
        //此处会用回UTF-8
        System.err.println(new String(b));
    }
}

/**
 * 解决乱码 只需要显示的指明字符集即可
 */
class UTFCode2 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str="中文是否乱码";
        //编辑器默认是UTF-8,此处使用GBK
        byte[] b=str.getBytes("GBK");
        //此处显示声明使用GBK
        System.err.println(new String(b,"GBK"));
    }
}

/**
 * 中文字符集v1.0版本--GB2312
 * 中文字符集v2.0版本--GBK
 * 中文字符集v3.0版本--GB18030
 * 版本向下兼容，只是包含的汉字数量不同
 */
class UTFCode3 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str="中文是否乱码";
        //编辑器默认是UTF-8,此处使用GBK
        byte[] b=str.getBytes("GB2312");
        //此处显示声明使用GBK
        System.err.println(new String(b,"GBK"));
        System.err.println(new String(b,"GB18030"));
    }
}