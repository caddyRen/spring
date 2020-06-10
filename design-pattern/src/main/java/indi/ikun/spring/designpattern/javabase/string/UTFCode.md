# 建议使用UTF编码
## java文件编码
```text
使用记事本创建.java后缀的文件，则文件的编码格式就是操作系统默认的格式。
如果是使用IDE工具创建的，则依赖于IDE的设置
```
## class文件编码
```text
通过javac命令生成的.class字节码文件是UTF-8编码的UNICODE文件,与操作系统无关
UTF是UNICODE的存储和传输格式，是为了解决UNICODE的高位占用冗余空间而产生的
使用UTF编码就标志着字符集使用的是UNICODE
```
## javac
- 不指定encoding 默认使用系统的编码，windows=GBK，java文件编码格式最好与encoding指定的一致，否则class文件里的中文是乱码
```shell script
javac -encoding GBK GBKCode.java
```
## javap 
- 读class文件，有点像反编译，但是不够完整 可以借助IDEA阅读
```shell script
javap GBKCode
```
