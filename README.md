# jee4a-user-service

微服务示例 user-service<br>

1、<br>
前提：<br>
需启动服务注册中心：jee4a-eureka-server <br>

2、<br>
打包：<br>
cd jee4a-user-service<br>
mvn clean install -D maven.test.skip=true<br>

3、启动两个实例：<br>

java -jar target/jee4a-user-service-0.0.1-SNAPSHOT.jar --server.port=8081<br>

java -jar target/jee4a-user-service-0.0.1-SNAPSHOT.jar --server.port=8082<br>

4、访问：<br>
http://localhost:8081/user/1 <br>
http://localhost:8082/user/1