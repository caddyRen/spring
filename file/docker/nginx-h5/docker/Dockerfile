FROM nginx:stable-alpine
# 定义作者
MAINTAINER caddy_ren <1584114193@qq.com>

WORKDIR /react

ENV TZ=Asia/Shanghai \
    LANG=en_US.UTF-8

# 将h5_page文件中的前端打包好的文件内容复制到 /usr/share/nginx/html/ 这个目录下面
COPY h5_page  /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/nginx.conf
RUN echo 'echo init ok!!'
