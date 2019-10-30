[TOC]
# Centos7 vsftpd服务器搭建
## install
```
yum -y install vsftpd
systemctl enable vsftpd
```
## config
- 不允许匿名登录
  ```
  vi /etc/vsftpd/vsftpd.conf
  ```
  anonymous_enable=NO

- 创建ftp用户
  ```
  useradd ftpuser
  passwd password
  ```

- 不允许用户ssh登录系统，但是能登陆vsftpd
  ```
  which nologin
  vi /etc/shells
  将which nologin 显示的内容加到shells文件末尾
  usermod -s /usr/sbin/nologin ftpuser
  测试： su -ftpuser
  systemctl restart vsfypd
  ```
- 开放防火墙策略
  ```
  firewall-cmd --zone=public --add-service=ftp --permanent
  firewall-cmd --reload
  ```

