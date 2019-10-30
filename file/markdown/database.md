#DataBase

##mysql

- 导出sql文件 -d 不导出数据 --add-drop-table 在create语句前加drop table
    - linux
    ```sql
    mysqldump -u DBUser -p databaseName > dbName.sql
    mysqldump -u DBUser -p databaseName tableName > tableName.sql
    mysqldump -u DBUser -p  -d --add-drop-table databaseName tableName > databaseName.sql
    ```
- 导入sql文件
    - linux
    ```sql
    mysql -u root -p 
    create database dbName;
    use dbName;
    set names utf-8;
    source /root/dbName.sql;
    
    or
    
    mysql -u root -p dbName < dbName.sql
    ```
    - 描述\
    进入到“mysql>”，输入命令"show databases；"，回车，看看有些什么数据库；\
    建立你要还原的数据库，输入"create database voice；"，回车；\
    切换到刚建立的数据库，输入"use voice；"，回车；导入数据，输入"source voice.sql；"，回车，开始导入
   
- 修改
    - 添加主键
    ```sql
    alter table t_name add primary key(id);
    ```
    - 修改字段位置
    ```sql
    alter table t_name modify id int first;(最前)
    alter table t_name modify id int after name;(id移到name后)
    ```
    - 修改字段名字及属性
    ```sql
    alter table t_name change id new_id varchar(10);(把id改成new_id且字段类型为varchar(10))
    ```
    - 删除字段
    ```sql
    alter table t_name drop column id;删除id
    ```
    - 重命名表
    ```sql
    alter table t_name rename new_name;
    ```
    - 增 
    ```sql
    insert into t_name values('','','','');
    ```
    - 删 
    ```sql
    delete from t_name where id=1111;
    ```
    - 改 
    ```sql
    update t_name set id=110;
    ```
    - 查 
    ```sql
    select * from t_name where time<'12:00:00'and date='2016-05-13';
    select name as '姓名' from student where id=1;
    ```

##oracle