package indi.ikun.spring.basejava.codequality.chapter01;

import javax.tools.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 17.慎用动态编译
 * java6 开始支持动态编译，可以在运行期直接编译java文件，执行class，并且能够获得相关的输入输出，甚至还能监听相关的事件
 * Java的动态编译对源提供了多个渠道。
 * 比如，可以是字符串（例子中就是字符串），
 * 可以是文本文件，
 * 也可以是编译过的字节码文件（.class文件），
 * 甚至可以是存放在数据库中的明文代码或是字节码。
 * 只要是符合Java规范的就都可以在运行期动态加载，
 * 其实现方式就是实现JavaFileObject接口，
 *      重写getCharContent、openInputStream、openOutputStream，
 * 或者实现JDK已经提供的两个SimpleJavaFileObject、ForwardingJavaFileObject
 *
 * 注意
 * （1）在框架中谨慎使用
 *      比如要在Struts中使用动态编译，动态实现一个类，它若继承自ActionSupport就希望它成为一个Action。能做到，但是debug很困难；
 *      再比如在Spring中，写一个动态类，要让它动态注入到Spring容器中，这是需要花费老大功夫的。
 * （2）不要在要求高性能的项目使用动态编译
 *      毕竟需要一个编译过程，与静态编译相比多了一个执行环节，因此在高性能项目中不要使用动态编译。
 *      不过，如果是在工具类项目中它则可以很好地发挥其优越性，
 *      比如在Eclipse工具中写一个插件，就可以很好地使用动态编译，不用重启即可实现运行、调试功能，非常方便。
 * （3）动态编译要考虑安全问题
 *      如果你在Web界面上提供了一个功能，允许上传一个Java文件然后运行，那就等于说：“我的机器没有密码，大家都来看我的隐私吧”，这是非常典型的注入漏洞，
 *      只要上传一个恶意Java程序就可以让你所有的安全工作毁于一旦。
 * （4）记录动态编译过程建议记录源文件、目标文件、编译过程、执行过程等日志，
 *      不仅仅是为了诊断，还是为了安全和审计，对Java项目来说，空中编译和运行是很不让人放心的，留下这些依据可以更好地优化程序。
 *
 */
public class Aq {

    public static void main(String[] args) throws Exception {
        //java源代码
        String sourceStr="public class Hello{public String sayHello(String name){return \"Hello,\"+name+\"!\";}}";
        //类名及文件
        String clsName="Hello";
        //方法名
        String methodName="sayHello";
        //当前编译器
        JavaCompiler cmp=ToolProvider.getSystemJavaCompiler();
        //Java标准文件管理器
        StandardJavaFileManager fm=cmp.getStandardFileManager(null,null,null);
        //Java文件对象
        JavaFileObject jfo=new StringJavaObject(clsName,sourceStr);
        //编译参数，类似于javac <options> 中的options
        List<String> optionsList=new ArrayList<>();
        //编译文件的存放地方，注意：此处是Eclipse工具特设的
//        optionsList.addAll(Arrays.asList("-d","./bin"));
        optionsList.addAll(Arrays.asList("-d","d:/"));
        //要编译的单元
        List<JavaFileObject> jfos=Arrays.asList(jfo);
        //设置编译环境
        JavaCompiler.CompilationTask task=cmp.getTask(null,fm,null,optionsList,null,jfos);
        //编译成功
        if(task.call()){
            //生产对象
            Object obj=Class.forName(clsName).newInstance();
            Class<? extends Object> cls=obj.getClass();
            //调用sayHello方法
            Method m=cls.getMethod(methodName,String.class);
            String str=(String)m.invoke(obj,"Dynamic Compilation");
            System.err.println(str);
        }
    }

}

//文本中的java对象
class StringJavaObject extends SimpleJavaFileObject{
    //源代码
    private String content="";
    //遵循java规范的类名和文件
    public StringJavaObject(String _javaFilleName,String _content){
        super(_createStringJavaObjectUri(_javaFilleName),Kind.SOURCE);
        content=_content;
    }
    //产生一个URL资源路径
    private static URI _createStringJavaObjectUri(String name){
        //注意此处没有设置包名
        return URI.create("String:///indi/ikun/spring/basejava/codequality/"+name+Kind.SOURCE.extension);
    }
    //文本文件代码
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors)throws IOException {
        return content;
    }
}