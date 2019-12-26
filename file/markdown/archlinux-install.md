[toc]
#archlinux 安装记录
##相关知识
- BIOS引导
    - MBR分区表+BIOS启动
    ```text
    MBR把硬盘分为很多区块，同时这些区块都配上对应逻辑块地址，
    MBR分区下每一个分区表的大小是固定的，MBR的第一个扇区内存有启动代码
    启动代码是BIOS启动系统的关键，它负责引导启动操作系统
    主引导代码--》第一个分区信息--》第二个分区信息--》第三个分区信息--》第四个分区信息--》硬盘有效标志
    ```
    - BIOS
    ```text
    BIOS : Basic Input Output System基本输入输出系统。简单来说它用于加载电脑最基本的程序代码，而这些代码是存储在CMOS里面的，他同时初始化硬件，检测硬件功能及引导操作系统的任务。
    ```
- UEFI引导
    - GPT分区表
    ```text
    GPT全称GUID磁盘分区表，表示这块分区表时全局唯一标识磁盘分区表。
    分区表大小不固定，硬盘容量不再限制于2TB。
    在GPT硬盘第一个数据块中也有MBR的引导表示也叫做PMBR
    UEFI+GPT的系统不需要主引导记录这些东西，开机会快很多。
    
    ```
    - UEFI启动
    ```text
    UEFI：Unified Extensible Firmware Interface
    统一的可扩展固件接口，可以直接预启动开始加载操作系统
    可以把很多开机程序都省去（BIOS的加电自检，硬件初始化）
    UEFI图形化界面更加直观，操作者进入BIOS后可以更直观查看各项数据（暂时理解为台式机的EFI BIOS系统界面）
    UEFI启动不需要活动分区，不需要主引导记录，所以UEFI比大多数BIOS快，
    区别主要在启动过程不同，UEFI省略很多的自检步骤，硬盘分区下他们也不同
    ```

##dd启动盘制作
- 查看U盘
```shell script
fdisk -l
```
- 如果u盘挂载，卸载掉，否则会提示设备资源正忙
```shell script
umount /dev/sdd*
```
- 格式化u盘vfat格式文件系统 centos7 提示找不到目录
```shell script
mkfs  -t vfat -f /dev/sdd1
```
- 写入iso镜像到U盘
```shell script
dd if=/root/archlinux.iso of=/dev/sdd1
```
- 查看进度和读写速度 新终端执行
```shell script
watch -n 5 pkill -USR1 ^dd$
```
##安装系统
- 联网,配国内镜像源
```shell script
联网
ip link
wifi-menu
更新时间
timedatectl set-ntp true
```
- 验证启动模式
```shell script
ls /sys/firmware/efi/efivars
```
- 看分区
```shell script
fdisk -l
fdisk -l /dev/sdb
lsblk
```
- 分区
    - bios模式
    ```shell script
    fdisk /dev/sdb
    一个系统安装分区---sdb1
    mkfs.ext4 /dev/sdb1
    mount /dev/sdb1 /mnt
    ```
    - UEFI模式
    ```shell script
    fdisk /dev/sdb
    p主分区e扩展分区
    引导分区512M---sdb1
    +
    系统安装分区---sdb2
    mkfs.fat -F32 /dev/sdb1
    mkfs.ext4 /dev/sdb2 
    mount /dev/sdb2 /mnt
    mkdir -p /mnt/efi
    mount /dev/sdb1 /mnt/efi
    ```
    - 格式化时报错/dev/sdb5 is apparently in use by the system 解除占用
    ```shell script
    cat /proc/partitions 
    dmsetup status
    dmsetup remove_all
    dmsetup status
    ```
- 安装系统
```shell script
vim /etc/pacman.d/mirrorlist
开头
Server = https://mirrors.tuna.tsinghua.edu.cn/archlinux/$repo/os/$arch
Server = https://mirrors.163.com/archlinux/$repo/os/$arch
vim /etc/pacman.conf
末尾
Server = https://mirrors.tuna.tsinghua.edu.cn/archlinuxcn/$arch
pacstrap /mnt base linux linux-firmware
      安装完base报错failed to install packages to new root
      pacman-key --refresh-keys
生成挂载信息文件
genfstab -U /mnt >> /mnt/etc/fstab
切换到新系统
arch-chroot /mnt
设置时区
ln -sf /usr/share/zoneinfo/Region/City /etc/localtime
hwclock --systohc
本地化
安装vim
pacman -S vim
vim /etc/locale.gen
en_US.UTF-8 UTF-8
zh_CN.UTF-8 UTF-8
zh_TW.UTF-8 UTF-8
生成locale信息
locale-gen
设置locale
vim /etc/locale.conf
LANG=en_US.UTF-8
主机名
vim /etc/hostname
caddy
hosts设置
vim /etc/hosts
127.0.0.1	localhost
::1		localhost
127.0.1.1	myhostname.localdomain	myhostname
设置密码
passwd
添加用户
useradd -m caddy
passwd caddy
安装必要软件联网+ssh
pacman -S networkmanager dhcpcd net-tools
systemctl enable NetworkManager
有线网络
systemctl enable dhcpcd
无线网络
pacman -S dialog wpa_supplicant
pacman -S openssh
systemctl enable sshd.service
pacman -S sudo
vim /etc/sudoers
+ caddy   ALL=(ALL) ALL

安装grub
pacman -S grub efibootmgr
设置引导
grub-install --target=x86_64-efi --efi-directory=efi --bootloader-id=GRUB
生成grub配置
grub-mkconfig -o /boot/grub/grub.cfg
exit
umount -R /mnt/efi
umount -R /mnt
lsblk
reboot
检查是否能正常进入系统
```

- 图形化界面KDE
```shell script
wifi-menu连不上使用
nmcli dev wifi list
nmcli device wifi connect "your wifi name" password "your wifi password"
查看显卡型号
lspci | grep VGA
# 官方仓库提供的驱动包：
# # +----------------------+--------------------+--------------+
# # |                      |        开源        |     私有     |
# # +----------------------+--------------------+--------------+
# # |         通用         |   xf86-video-vesa  |              |
# # +----------------------+--------------------+--------------+
# # |         Intel        |  xf86-video-intel  |              |
# # +--------+-------------+--------------------+--------------+
# # |        | GeForce 9+  |                    |    nvidia    |
# # +        +-------------+                    +--------------+
# # | nVidia | GeForce 8/9 | xf86-video-nouveau | nvidia-340xx |
# # +        +-------------+                    +--------------+
# # |        | GeForce 6/7 |                    | nvidia-304xx |
# # +--------+-------------+--------------------+--------------+
# # |        AMD/ATI       |   xf86-video-ati   |              |
sudo pacman -S xf86-video-intel（笔记本）
pacman -S xorg xorg-server xorg-xinit xorg-apps
pacman -S plasma kde-applications
pacman -S sddm sddm-kcm
systemctl enable sddm
```
