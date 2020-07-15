FROM server-jre:8-alpine-1.0.0

MAINTAINER caddy_ren <1584114193@qq.com>

WORKDIR /springboot

# 目标jar及sh脚本拷贝至工作目录
COPY *.jar *.sh ./

# 设置启动容器时默认执行启动脚本
CMD ["sh","start.sh"]
