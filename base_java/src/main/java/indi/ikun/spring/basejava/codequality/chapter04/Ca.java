package indi.ikun.spring.basejava.codequality.chapter04;


/**
 * 53.注意方法中传递的参数要求
 * 有这样一个简单需求：
 * 写一个方法，实现从原始字符串中删除与之匹配的所有子字符串，
 * 比如在“蓝蓝的天，白云飘”中，删除“白云飘”，输出“蓝蓝的天，”
 *
 * 注意：replaceAll传递的第一个参数是正则表达式
 */
public class Ca {
    public static void main(String[] args) {
        System.err.println(remove("好是好","好").equals("是"));
        System.err.println(remove("$是$","$").equals("是"));

        System.err.println(remove2("好是好","好").equals("是"));
        System.err.println(remove2("$是$","$").equals("是"));
    }
    //删除字符串，有问题
    public static String remove(String source,String sub){
        /**
         * replaceAll方法上，该方法确实需要传递两个String类型的参数，也确实进行了字符串替换，
         * 但是它要求第一个参数是一个正则表达式，符合正则表达式的字符串才会被替换。
         * 对上面的例子来说，第一个测试案例传递进来的是一个字符串“好”，这是一个全匹配查找替换，处理得非常正确，
         * 第二个测试案例传递进来的是“$”符号，“$”符号在正则表达式中表示的是字符串的结束位置，
         *      也就是说执行完repalceAll后，在字符串结尾的地方加上了空字符串，其结果还是“$是$”，
         *      所以测试失败也就在所难免了。
         * 问题清楚了，解决方案也就出来了：
         *      使用replace方法替代即可，
         *      它是repalceAll方法的简化版，
         *      可传递两个String参数继续替换，与我们的编码意图是相吻合的
         */
        return source.replaceAll(sub,"");
    }

    //删除字符串
    public static String remove2(String source,String sub){
        /**
         *replace(CharSequencetarget,CharSequence replacement)方法是在1.5版本以后才开始提供的，
         * 在此之前如果要对一个字符串进行全替换，只能使用replaceAll方法，
         * 不过由于replaceAll方法的第一个参数使用了正则表达式，
         * 而且参数类型只要是CharSequence就可以（String的父类），
         * 所以很容易让使用者误解，稍有不慎就会导致严重的替换错误
         */
        return source.replace(sub,"");
    }
}
