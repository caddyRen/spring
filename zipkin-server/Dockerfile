FROM server-jre:8-alpine-1.0.0

MAINTAINER renqiankun <1584114193@qq.com>

WORKDIR /zipkin-server

# 目标jar及sh脚本拷贝至工作目录
COPY build/libs/*.jar ./
COPY bashfiles/*.sh ./

# 设置启动容器时默认执行启动脚本
CMD ["sh","start.sh"]