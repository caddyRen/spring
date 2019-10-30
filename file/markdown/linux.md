#Linux基础命令教程
##Shell:即工作环境
1. user通过shell操作硬件,windows操作系统只是把shell封装成可视化界面进行与硬件交互
2. 用户登陆 ~ 表示当前位置/用户名；/ 表示根目录；pwd查看当前路径。#为root提示符号；$为普通用户
2. 命令
    1. command 【option】命令选项 【arguments】命令参数  例如ls -l / 查看/目录下的目录详细信息
    2. date 时间
    3. cal 日历
    4. bc 计算器
    5. man cal 和info cal 查看cal命令的帮助文档
3. 关机命令
    1. shutdown -h 关机 -r 重启 -f 快速重启
        1. hh:mm执行时间 21:23 21点23分执行
        2. +m  m分钟后执行
        3. now 即+0 立即执行
        4. shutdown -h +10 'the system will shutdown'还可以加广播提示其他用户
    2. centos arch可以执行poweroff关机
4. 显示文件系统内容
    1. Linux提供多用户多任务环境操作实现，文件可存取访问的身份owner，group，other，权限read，write，execute（可执行）
       1. read = r = 4
       2. write = w = 2
       3. execute = x =1
       
    1. ls -l 显示当前目录更多文件信息
    2. ls -a 显示当前目录隐藏文件
    3. ls -al 显示当前目录全部文件+文件信息
       1. drwxr-xr-x.  2 root root     6 Jul 23 10:12 study

       |文件权限|链接数|文件所有者|文件所属用户组|文件大小|文件最后修改时间|文件名|
       |:---:|:---:|:---:|:---:|:---:|:---:|:---:|
       |drwxr-xr-x|2|root|root|6|Jul 23 10:12|study|
       2. 文件权限
       
       |d|rwx|r-x|r-x|
       |:---:|:---:|:---:|:---:|
       |文件类型|文件所有者权限|文件所属用户组权限|其他人对此文件的权限|
       3. 文件类型
          1. d:目录
          2. -:文件
             1. 纯文本文件（ASCII）
             2. 二进制文件（binary）
             3. 数据格式文件（date）
          3. i:连接文件，类似windows下的快捷方式
          4. b:设备与设备文件，可存储接口设备与系统外设及存储相关/块设备，在/dev下
          5. c:串行端口设备，字符设备文件
          6. s:套接字，数据接口文件，网络上的数据连接 /var/run
          7. p:管道（FIFO，PIPE） 解决多个程序访问一个文件时造成的错误问题
       3. 文件扩展名:只是为了让用户了解文件的用途
          - linux文件是没有所谓的扩展名的，一个文件是否执行与文件权限的【drwxr-xr-x】十个属性相关，有x属性则表示可以执行，但是可以执行与执行成功并不一致
          - 常用扩展名
             1. *.sh 脚本或批处理文件script 脚本是用shell写成的
             2. *.Z,*.tar,*.tar.gz,*.zip,*.tg经打包的压缩文件
       4. 改变文件属性和权限
          1. 改变文件所属用户组 charg groupName file
          2. 改变文件所有者 chown userName file
          3. 改变文件的权限 chmod 777 file
             1. 权限计算方法:777是rwxrwxrwx的累加，如下
                1. owner=rwx=4+2+1=7
                2. group=rwx=4+2+1=7
                3. other=rwx=4+2+1=7
       5. 文件种类
          
    4. ls -a -l /  显示/目录下的文件
    5. ls -F 列出目录下文件名及其目录类型（后跟*号表示可执行文件，@表示符号连接/表示目录名）
    6. ls -t 按照最后修改文件时间列出文件名
    7. ls -R 列出当前目录及其子目录的文件名
5. 操作目录和文件
    1. mkdir myfile 在当前目录下创建目录myfile
    2. rmdir myfile 删除当前目录下的myfile目录（myfile目录下必须为空才能删除成功）
    3. cd .. 返回上级目录   . 表示当前目录 .. 表示上级目录
    4. cp XX.log ./one 复制当前目录下的XX.log文件到 ./ 目录下的one目录内 即当前目录下的one目录内
    5. cp -r one two 当前目录下将目录one复制到two内 -r表示可以复制目录及目录内的所有文件（递归）
    6. cp xx.log xx.log.bak 当前目录内复制xx.log并将新复制的文件改名为xx.log.bak
    7. mv xx.log ./one 将当前目录下的xx.log文件移到当前目录下的one目录内
    7. mv xx.log xx.syslog 将当前目录下的xx.log移到当前目录下，并改名为xx.syslog（改名字）
    7. touch 1.txt 在当前目录下创建1.txt文件
    7. file 1.txt 查看文件类型
    7. rm -f 1.txt 删除当前目录下的1.txt文件，强制删除不是递归
    7. rm -r 循环删除目录，直到该目录下内容全部被删除，即递归
    7. rm -f 1*.txt 删除当前目录下所有1开头的.txt文件 *号匹配任意字符和字符串
    7. rm -f 1?.txt 删除当前目录下1开头的.txt文件 ？号匹配一个字符
    7. rm -f 11[1-3].txt 删除当前目录下以11开头1，2，3结尾的.txt文件[abc]表示a,b,c任一字符[a-x]a到x的任一字符[1-9]1到9任一数字
    7. rm -f 1[!1]*.txt 删除当前目录下第二个字符不是1的.txt文件 与上一条含义相反
    7. locate xx.log 搜索xx.log文件；此操作需要数据库支持，默认7天更新一次，也可以手动更新执行updatedb需耗时
    7. pwd 打印当前所在目录print working directory
6. 用户：只有root可以设置其他用户密码，其他用户只能修改自己的密码
    1. useradd caddy  增加caddy用户（root）
    2. passwd caddy 修改caddy密码（root）
    1. passwd 修改自己密码，不用跟用户名（caddy）
7. 显示文件内容
    1. cat xx.log 适合显示内容少的，如果太多行，只会显示后面的部分（看不全）
    2. more xx.log 解决cat显示不全的问题   按space翻页 q键退出（不能倒退查看已经看过的内容）
    3. less xx.log 解决more不能上下翻页的问题  PageUp PageDown翻页 q退出
    4. head xx.log显示文件前几行（默认10行）
    5. tail xx.log显示文件最后几行（默认10行）
    6. head/tail -10 xx.log 显示前/后10行
    7. tac xx.log 从最后一行开始显示文件内容
    8. nl xx.log 显示并输出行号
    9. od xx.log 以二进制读取文件
8. vi编辑器:linux下全屏幕文本编辑，特殊按键esc退出编辑模式进入命令模式，字母I进入编辑模式，符号：进入末行模式；输入字母q直接退出，w保存，wq保存退出
    1. 如异常退出编辑（比如Ctrl+c）,会有临时文件在内存中，直接找到文件(一般都是.开头的隐藏文件) rm -rf xxx.swp
    1. vi 不加文件显示vi版本信息
    2. 保存文件时如果有文件名则直接保存，没有则使用w xxx保存，xxx代表文件名
    3. Ctrl+F向前翻整页；Ctrl+B向后翻整页；Ctrl+U向前翻半页；Ctrl+D向后翻半页
    4. ^移到行首；$移到行尾
    5. 末行模式 set nu显示行号，set nonu取消行号
    6. 命令模式 1G到首行，G到尾行，#G到#行
    7. 命令模式下删除操作
        1. x删除光标处的单个字符
        2. dd删除光标所在行
        3. dw删除当前字符到单词尾包括空格的所有字符
        4. 3x删除光标初向右的三个字符
        4. 3dd删除从当前行向下三行
        5. u小写撤销最近一次操作
        5. U大写撤销所有操作
        6. Ctrl+R恢复u小写的撤销操作
    8. 命令模式下复制操作
        1. yy复制当前行到vi缓冲区
        1. yw复制当前光标所在位置到单词尾字符内容到vi缓冲区（复制一个单词）
        1. y$复制光标到行尾内容
        1. y^复制光标到行首内容
        1. 6yy复制5行
        1. 2yw复制两个单词
    9. 末行模式复制指定行 3，5y复制第三行到第5行内容
