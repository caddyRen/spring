FROM alpine:3.10.2

MAINTAINER caddy_ren <1584114193@qq.com>

ENV JAVA_VERSION_MAJOR=7 \
    JAVA_VERSION_MINOR=60 \
    JAVA_VERSION_BUILD=11 \
    GLIBC_VERSION=2.30-r0 \
    ALPINE_VERSION=3.10 \
    JAVA_PACKAGE=server-jre \
    JAVA_PACKAGE_VARIANT=nashorn \
    JAVA_JCE=unlimited \
    JAVA_HOME=/opt/jdk \
    PATH=${PATH}:/opt/jdk/bin \
    GLIBC_REPO=https://github.com/sgerrand/alpine-pkg-glibc \
    TZ=Asia/Shanghai \
    LANG=en_US.UTF-8

COPY ./${JAVA_PACKAGE}-${JAVA_VERSION_MAJOR}u${JAVA_VERSION_MINOR}-linux-x64.tar.gz /tmp/java.tar.gz
COPY ./glibc-${GLIBC_VERSION}.apk /tmp/glibc-${GLIBC_VERSION}.apk
COPY ./glibc-bin-${GLIBC_VERSION}.apk /tmp/glibc-bin-${GLIBC_VERSION}.apk
COPY ./glibc-i18n-${GLIBC_VERSION}.apk /tmp/glibc-i18n-${GLIBC_VERSION}.apk
COPY ./sgerrand.rsa.pub /etc/apk/keys/sgerrand.rsa.pub


RUN set -ex && \
    echo "http://mirrors.ustc.edu.cn/alpine/v${ALPINE_VERSION}/main" >> /etc/apk/repositories && \
    cat /etc/apk/repositories &&\
    apk -U upgrade && \
    apk add --no-cache tzdata libstdc++ curl ca-certificates bash java-cacerts openssh-client lsof && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/shanghai" >> /etc/timezone && \
    apk add --allow-untrusted /tmp/*.apk && \
    rm -v /tmp/*.apk && \
    ( /usr/glibc-compat/bin/localedef --force --inputfile POSIX --charmap UTF-8 ${LANG} || true ) && \
    echo "export LANG=${LANG}" > /etc/profile.d/locale.sh && \
    /usr/glibc-compat/sbin/ldconfig /lib /usr/glibc-compat/lib && \
    gunzip /tmp/java.tar.gz && \
    tar -C /opt -xf /tmp/java.tar && \
    ln -s /opt/jdk1.${JAVA_VERSION_MAJOR}.0_${JAVA_VERSION_MINOR} /opt/jdk && \
    find /opt/jdk/ -maxdepth 1 -mindepth 1 | grep -v jre | xargs rm -rf && \
    cd /opt/jdk/ && ln -s ./jre/bin ./bin && \
    sed -i s/#networkaddress.cache.ttl=-1/networkaddress.cache.ttl=10/ $JAVA_HOME/jre/lib/security/java.security && \
    apk del glibc-i18n && \
    rm -rf /opt/jdk/jre/plugin \
           /opt/jdk/jre/bin/javaws \
           /opt/jdk/jre/bin/orbd \
           /opt/jdk/jre/bin/pack200 \
           /opt/jdk/jre/bin/policytool \
           /opt/jdk/jre/bin/rmid \
           /opt/jdk/jre/bin/rmiregistry \
           /opt/jdk/jre/bin/servertool \
           /opt/jdk/jre/bin/tnameserv \
           /opt/jdk/jre/bin/unpack200 \
           /opt/jdk/jre/lib/javaws.jar \
           /opt/jdk/jre/lib/deploy* \
           /opt/jdk/jre/lib/desktop \
           /opt/jdk/jre/lib/*javafx* \
           /opt/jdk/jre/lib/*jfx* \
           /opt/jdk/jre/lib/amd64/libdecora_sse.so \
           /opt/jdk/jre/lib/amd64/libprism_*.so \
           /opt/jdk/jre/lib/amd64/libfxplugins.so \
           /opt/jdk/jre/lib/amd64/libglass.so \
           /opt/jdk/jre/lib/amd64/libgstreamer-lite.so \
           /opt/jdk/jre/lib/amd64/libjavafx*.so \
           /opt/jdk/jre/lib/amd64/libjfx*.so \
           /opt/jdk/jre/lib/ext/jfxrt.jar \
           /opt/jdk/jre/lib/oblique-fonts \
           /opt/jdk/jre/lib/plugin.jar \
           /tmp/* /var/cache/apk/* /etc/apk/keys/sgerrand.rsa.pub && \
    ln -sf /etc/ssl/certs/java/cacerts $JAVA_HOME/jre/lib/security/cacerts && \
    echo 'hosts: files mdns4_minimal [NOTFOUND=return] dns mdns4' >> /etc/nsswitch.conf

# EOF
