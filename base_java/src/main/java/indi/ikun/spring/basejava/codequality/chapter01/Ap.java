package indi.ikun.spring.basejava.codequality.chapter01;

import javax.script.*;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 16.易变业务使用脚本语言编写
 * 脚本语言特性
 *  灵活。脚本语言一般都是动态类型，可以不用声明变量类型而直接使用，也可以在运行期改变类型
 *  便捷。脚本语言是一种解释型语言，不需要编译成二进制代码，也不需要像Java一样生成字节码。它的执行是依靠解释器解释的，因此在运行期变更代码非常容易，而且不用停止应用
 *  简单。只能说部分脚本语言简单，比如Groovy，Java程序员若转到Groovy程序语言上，只需要两个小时，看完语法说明，看完Demo即可使用了，没有太多的技术门槛
 * 脚本语言的这些特性是Java所缺少的，引入脚本语言可以使Java更强大，
 * 于是Java 6开始正式支持脚本语言。
 * 但是因为脚本语言比较多，Java的开发者也很难确定该支持哪种语言，
 * 于是JCP（Java Community Process）很聪明地提出了JSR223规范，
 * 只要符合该规范的语言都可以在Java平台上运行（它对JavaScript是默认支持的）
 * 诸位读者有兴趣的话可以自己写个脚本语言，然后再实现ScriptEngine，即可在Java平台上运行
 *
 *
 */
public class Ap {

    public static void main(String[] args) throws Exception{
        //获取一个JavaScript的执行引擎
        ScriptEngine engine=new ScriptEngineManager().getEngineByName("javascript");
        //建立上下文变量
        Bindings bind=engine.createBindings();
        bind.put("factor",1);
        //绑定上下文，作用域是当前引擎范围
        engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);
        Scanner input=new Scanner(System.in);
        while (input.hasNextInt()){
            int first=input.nextInt();
            int sec=input.nextInt();
            System.err.println("输入参数是："+first+","+sec);
            //执行js代码
            engine.eval(new FileReader("d:/Ap.js"));//win
//            engine.eval(new FileReader("/onepiece/Ap.js"));//arch;
            //是否可回调方法
            if(engine instanceof Invocable){
                Invocable in=(Invocable)engine;
                //执行js中的函数
                Double result=(Double)in.invokeFunction("formula",first,sec);
                System.err.println("运算结果是"+result.intValue());
            }
        }
    }

}