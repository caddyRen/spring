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
  firewall-cmd --reload:q
  ```
- 修改sshd配置```vi /etc/ssh/sshd_config```
```text
LoginGraceTime 0
MaxSessions 1000
Subsystem sftp internal-sftp
Match group ppmuser
ChrootDirectory /home/ppmuser
X11Forwarding no
AllowTcpForwarding no
ForceCommand internal-sftp
ClientAliveInterval 60
ClientAliveCountMax 10
```

- 权限设置
  ```text
  ChrootDirectory设置的目录权限及其所有上级文件夹权限，属主和属组必须是root
  chown root:root /home/ppmuser
  ChrootDirectory只有属主能拥有写权限，权限最大设置只能是755
  chmod 755 /home/ppmuser
  ```

- 修改配置后要重启sshd 和vsftpd服务
```shell script
systemctl restart sshd
systemctl restart vsftpd

service sshd restart
service restart vsftpd
```

