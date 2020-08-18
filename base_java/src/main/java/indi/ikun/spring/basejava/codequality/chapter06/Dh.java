package indi.ikun.spring.basejava.codequality.chapter06;

/**
 * 86.在switch的default代码块中增加AssertionError错误
 *
 * switch后跟枚举类型，case后列出所有的枚举项，这是一个使用枚举的主流写法，
 * 那留着default语句似乎没有任何作用，
 * 程序已经列举了所有的可能选项，肯定不会执行到default语句，
 * 看上去纯属多余嘛！错了，这个default还是很有用的
 *
 *
 *
 */
public class Dh {
    public static void main(String[] args) {
        Dh dh = new Dh();
        dh.logLevel(LogLevelDh.FATAL);
    }
    /**
     * 由于把所有的枚举项都列举完了，不可能有其他值，
     * 所以就不需要default代码块了，这是普遍的认识，
     * 但问题是我们的switch代码与枚举之间没有强制约束关系，
     * 也就是说两者只是在语义上建立了联系，并没有一个强制约束，
     * 比如LogLevel枚举发生改变，增加了一个枚举项FATAL，
     * 如果此时我们对switch语句不做任何修改，编译虽不会出现问题，
     * 但是运行期会发生非预期的错误：FATAL类型的日志没有输出
     *
     * 避免出现这类错误，建议在default后直接抛出一个AssertionError错误
     */
    public void logLevel(LogLevelDh logLevel){
        switch (logLevel){
            case DEBUG:
                System.err.println("debug");
                break;
            case INFO:
                System.err.println("info");
                break;
            case WARN:
                System.err.println("warn");
                break;
            case ERROR:
                System.err.println("error");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + logLevel);
        }
    }
}
enum LogLevelDh{
    FATAL,DEBUG,INFO,WARN,ERROR;
}
