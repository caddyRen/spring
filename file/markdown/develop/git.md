#git
##command
1. 下载项目
    ```shell script
    git clone 项目地址
    ```
1. 查看本地分支
    ```shell script
    git branch
    ```
1. 查看远程分支
    ```shell script
    git branch -r
    ```
1. 查看所有分支
    ```shell script
    git branch -a
    ```
1. 创建远程开发分支的本地分支，并切换到开发分支
   ```shell script
   git checkout -b develop origin/develop
   ```
1. 创建本地分支
   ```shell script
   git branch temp
   ```
1. 切换本地分支
   ```shell script
   git checkout temp
   ```
1. 创建本地分支，并切换新分支，与远程分支无关
   ```shell script
   git checkout -b temp
   ```
1. 合并（merge）到哪个分支就要先切换到哪个分支，合并之前两个分支都要先commit。例如将temp分支合并到develop
   ```shell script
   git checkout develop
   git merge temp
   ```
1. 冲突时，手动处理冲突代码，git add 冲突文件相对路径 告诉git冲突已解决，再commit
   ```shell script
   git add eureka-server/src/main/java/indi/ikun/spring/eurekaserver/EurekaServerApplication.java
   git commit -am '合并冲突'
   ```   