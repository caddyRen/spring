package indi.ikun.spring.basejava.codequality.chapter04;


import java.io.UnsupportedEncodingException;

/**
 * 58.强烈建议使用UTF编码
 * Java中的编码规则:
 *  1.Java文件编码
 *      记事本创建一个.java后缀的文件，则文件的编码格式就是操作系统默认的格式。如果是使用IDE工具创建的，如Eclipse，则依赖于IDE的设置，Eclipse默认是操作系统编码（Windows一般为GBK
 *  2.Class文件编码
 *      通过javac命令生成的后缀名为.class的文件是UTF-8编码的UNICODE文件，
 *      这在任何操作系统上都是一样的，只要是class文件就会是UNICODE格式。
 *      需要说明的是，UTF是UNICODE的存储和传输格式，它是为了解决UNICODE的高位占用冗余空间而产生的，
 *      使用UTF编码就标志着字符集使用的是UNICODE
 */
public class Cf {
    public static void main(String[] args) {

    }
}
/**
 * Java乱码  一般都是字符集不一致造成的
 */
class UTFCode {
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