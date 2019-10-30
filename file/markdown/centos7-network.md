[toc]
#centos7
##网络问题
- vitualbox之间网络不通,
    - 获取ip``` dhclient``` 
    - 释放ip ``` dhclient -r  ```
    - 设置两个网卡，一个网络地址转换，一个本机Host-only
    - 修改
        ``` 
        vi /etc/sysconfig/network-scripts/ifcfg-enp0s3 
        修改如下内容：
        TYPE=Ethernet
        PROXY_METHOD=none
        BROWSER_ONLY=no
        BOOTPROTO=static
        DEFROUTE=yes
        IPV4_FAILURE_FATAL=no
        IPV6INIT=yes
        IPV6_AUTOCONF=yes
        IPV6_DEFROUTE=yes
        IPV6_FAILURE_FATAL=no
        IPV6_ADDR_GEN_MODE=stable-privacy
        NAME=enp0s3
        UUID=adda3749-c1da-46c3-9694-6277ccc9c396
        DEVICE=enp0s3
        ONBOOT=yes
        IPADDR=10.0.2.4
        NETMASK=255.255.255.0
        GATEWAY=10.0.2.1
        DNS1=114.114.114.114
        ZONE=public
       ```
    - 重启网络服务 ``` service network restart ``` 
    - 查看IP和dns ``` cat /etc/resolv.conf ``` 
    - ping www.badu.com 测试