package indi.ikun.spring.designpattern.pattern.behavior.interpreter;

import java.util.HashMap;
import java.util.Stack;

/**
 * 抽象表达式,声明 一个抽象的解释操作,
 * 这个方法为抽象语法树中所有的节点所共享
 * 通过hashMap键值对,可以获取到变量的值
 */
public abstract class Expression {

    /**
     * a+b-c
     * 解释公式和数值,key是公式,参数a.b.c
     *
     * @param var {a=10,b=20}
     * @return
     */
    public abstract int interpreter(HashMap<String, Integer> var);
}

/**
 * 变量的解释器
 * 终结符表达式,实现与文法中的终结符相关的解释操作
 */
class VarExpression extends Expression {

    //key=a.key=b.key=c
    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    /**
     * 根据变量名,返回对应的值
     *
     * @param var {a=10,b=20}
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(key);
    }
}

/**
 * 符号解释器,每个运算符的符号,都只和自己左右两个数字有关系
 * 但左右两个数字有可能也是一个解析结果,无论何种类型,都是Expression的实现类
 *
 * 非终结符表达式,实现与文法中的非终结符相关的解释操作
 */
class SymbolExpression extends Expression {

    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * 此类是让子类来实现,因此此处的interpreter为默认实现
     * @param var {a=10,b=20}
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}

/**
 * 减法 解释器
 */
class SubExpression extends SymbolExpression {
    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }
    /**
     * 处理减法
     * @param var {a=10,b=20}
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        //实际调用的是VarExpression的interpreter方法
        return super.left.interpreter(var) - super.right.interpreter(var);
    }
}

/**
 * 加法 解释器
 */
class AddExpression extends SymbolExpression {

    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * 处理加法
     * @param var {a=10,b=20}
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        //实际调用的是VarExpression的interpreter方法
        return super.left.interpreter(var) + super.right.interpreter(var);
    }

}

/**
 * 环境,包含解释器之外的全局信息
 * <p>
 * 通过Client输入 Calculator和VarExpression信息
 */
class Calculator {

    private Expression expression;

    /**
     *
     * @param expStr a+b-c
     */
    public Calculator(String expStr) {

        Stack<Expression> stack=new Stack<>();
        //[a,+,b,-,c]
        char[] charArray=expStr.toCharArray();

        Expression left=null;
        Expression right=null;
        for(int i=0;i<charArray.length;i++){
            switch (charArray[i]){
                case '+':
                    //从stack取出left   a
                    left=stack.pop();
                    //取出右边表达式     b
                    right=new VarExpression(String.valueOf(charArray[++i]));
                    //将AddExpression对象加到stack
                    stack.push(new AddExpression(left,right));
                    break;
                case '-':
                    left=stack.pop();
                    right=new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left,right));
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                    break;
            }
        }
        //遍历完charArray stack就得到最后的Expression
        expression=stack.pop();
    }

    public int run(HashMap<String ,Integer> var){
        //将表达式和var绑定
        //传递给expression的interpreter进行解释执行
        return expression.interpreter(var);
    }

}