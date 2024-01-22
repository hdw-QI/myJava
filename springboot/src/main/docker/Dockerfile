# 基础镜像使用java8
FROM docker.io/openjdk:8
# 将jar包添加到容器中并使用app.jar作为名称
ADD *.jar app.jar
# 对外暴露的端口为8888
EXPOSE 8888
# 运行jar文件
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]