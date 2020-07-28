# JAVA 基础
# 编码质量目录
1. Aa不要在常量和变量中出现易混淆的字母
2. Ab莫让常量蜕变成变量
3. Ac三元操作符的类型务必一致
4. Ad避免带有变长参数的方法重载
5. Ae别让null值和空值威胁到变长方法
6. Af覆写变长方法也循规蹈矩
7. Ag警惕自增的陷阱
8. Ah不要让旧语法困扰你
9. Ai少用静态导入
10. Aj不要在本类中覆盖静态导入的变量和方法
11. Ak养成良好习惯，显式声明UID
12. Al避免用序列化类在构造函数中为不变量赋值
13. Am避免为final变量复杂赋值
14. An使用序列化类的私有方法巧妙解决部分属性持久化问题
15. Ao break万万不可忘
16. Ap易变业务使用脚本语言编写
17. Aq慎用动态编译
18. Ar避免instanceof非预期结果
19. As断言绝对不是鸡肋
20. At不要只替换一个类
21. Au用偶判断，不用奇判断
22. Av用整数类型处理货币
23. Aw不要让类型默默转换
24. Ax边界，边界，还是边界
25. Ay不要让四舍五入亏了一方
26. Az提防包装类型的null值
27. Ba谨慎包装类型的大小比较
28. Bb优先使用整型池
29. Bc优先选择基本类型
30. Bd不要随便设置随机种子
31. Be在接口中不要存在实现代码
32. Bf静态变量一定要先声明后赋值
33. Bg不要覆写静态方法
34. Bh构造函数尽量简化
35. Bi避免在构造函数中初始化其他类
36. Bj使用构造代码块精炼程序
37. Bk构造代码块会想你所想
38. Bl使用静态内部类提高封装性
39. Bm使用匿名类的构造函数
40. Bn匿名类的构造函数很特殊
41. Bo让多重继承成为现实
42. Bp让工具类不可实例化
43. Bq避免对象的浅拷贝
44. Br推荐使用序列化实现对象的拷贝
45. Bs覆写equals方法时不要识别不出自己
46. Bt equals应该考虑null值情景
47. Bu在equals中使用getClass进行类型判断
48. Bv覆写equals方法必须覆写hashCode方法
49. Bw推荐覆写toString方法
50. Bx使用package-info类为包服务
51. By不要主动进行垃圾回收
52. Bz推荐使用String直接量赋值
53. Ca注意方法中传递的参数要求
54. Cb正确使用String、StringBuffer、StringBuilder
55. Cc注意字符串的位置
56. Cd自由选择字符串拼接方法
57. Ce推荐在复杂字符串操作中使用正则表达式
58. Cf强烈建议使用UTF编码
59. Cg对字符串排序持一种宽容的心态
60. Ch性能考虑，数组是首选
61. Ci若有必要，使用变长数组
62. Cj警惕数组的浅拷贝
63. Ck在明确的场景下，为集合指定初始容量
64. Cl多种最值算法，适时选择
65. Cm避开基本类型数组转换列表陷阱
66. Cn asList方法产生的List对象不可更改
67. Co不同的列表选择不同的遍历方法
68. Cp频繁插入和删除时使用LinkedList
69. Cq列表相等只需关心元素数据
70. Cr子列表只是原列表的一个视图
71. Cs推荐使用subList处理局部列表
72. Ct生成子列表后不要再操作原列表
73. Cu使用Comparator进行排序





1. 包名全小写、类名首字母全大写、常量全部大写并用下划线分隔、变量采用驼峰命名法（Camel Case）;
2. 不要将易混字母混合使用iIlL10Oo(小写字母i、大写字母I、小写字母l、大写字母L、数字1、数字0、大写字母O、小写字母o)
1. 在面向对象编程（Object-Oriented Programming，OOP）的世界里，类和对象是真实世界的描述工具，方法是行为和动作的展示形式，封装、继承、多态则是其多姿多彩的主要实现方式
## 建议使用UTF编码
### java文件编码
```text
使用记事本创建.java后缀的文件，则文件的编码格式就是操作系统默认的格式。
如果是使用IDE工具创建的，则依赖于IDE的设置
```
### class文件编码
```text
通过javac命令生成的.class字节码文件是UTF-8编码的UNICODE文件,与操作系统无关
UTF是UNICODE的存储和传输格式，是为了解决UNICODE的高位占用冗余空间而产生的
使用UTF编码就标志着字符集使用的是UNICODE
```
### javac
- 不指定encoding 默认使用系统的编码，windows=GBK，java文件编码格式最好与encoding指定的一致，否则class文件里的中文是乱码
```shell script
javac -encoding GBK GBKCode.java
```
### javap 
- 读class文件，有点像反编译，但是不够完整 可以借助IDEA阅读
```shell script
javap GBKCode
```