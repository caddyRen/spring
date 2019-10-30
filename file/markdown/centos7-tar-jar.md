#tar & jar
##tar
- 不解压列出tar内容
```
tar -tvf **.tar 
```
- 将file文件夹添加到**.tar
```
tar -cvf **.tar ./file/ 
```
- 解压tar 到file
```
tar -xvf **.tar ./file 
```
##jar
- 解压war包
```
jar -xvf **.war
```