FROM eclipse-temurin:17-jre
VOLUME  /tmp
RUN  ln  -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN  echo  'Asia/Shanghai' >/etc/timezone
COPY ./target/coupon-customer-impl-1.0-SNAPSHOT.jar  app.jar
ENTRYPOINT  ["java","-jar","/app.jar"]