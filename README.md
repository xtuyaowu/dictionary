# SpringBoot 日常微信开发脚手架

使用`SpringBoot`做日常Web开发,根据自己的喜好配置了一套脚手架.主分支为最基础的稳定版本.
使用`Mybatis`和`Jpa` 做持久层框架
使用`Redis`做全局缓存,按照我自己的喜好配置好了KeyGenerator
使用`Swagger`生成api接口文档测试. 地址:`CONTEXT_PATH/swagger-ui.html`
使用`Druid`数据源,监控地址:`CONTEXT_PATH/druid/index.html`,账号`admin`密码`admin`(可在配置文件中修改)
使用`weixin4j`做微信开发整合,需要改动`weixin4j.properties`文件中的appid和secret. 
使用`Quartz2`做定时任务.

## 一些教程
[使用weixin4j获取用户信息教程](http://chenzhihao.cc/archives/175)
[使用weixin4j开发JSSDK教程](http://chenzhihao.cc/archives/193)

## 配置说明

首先,在`resources`目录下建立文件: `application.properties`,配置一下内容
```
#############################
#           Config          #
#############################
#应用名称
spring.application.name=项目名称
#启动Banner
banner.location=classpath:banner.txt
#项目根路径
server.context-path=/项目根路径


#############################
#           数据源           #
#############################
spring.datasource.url=jdbc:mysql://localhost:3306/数据库名?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.username=数据库账号
spring.datasource.password=数据库密码
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.druid.filters=stat
spring.datasource.druid.maxActive=20
spring.datasource.druid.initialSize=1
spring.datasource.druid.maxWait=60000
spring.datasource.druid.minIdle=10
spring.datasource.druid.timeBetweenEvictionRunsMillis=60000
spring.datasource.druid.minEvictableIdleTimeMillis=300000
spring.datasource.druid.validationQuery=SELECT 'x'
spring.datasource.druid.testWhileIdle=true
spring.datasource.druid.testOnBorrow=false
spring.datasource.druid.testOnReturn=false
spring.datasource.druid.PoolPreparedStatements=true
spring.datasource.druid.maxOpenPreparedStatements=20
spring.datasource.druid.removeAbandoned=true
spring.datasource.druid.removeAbandonedTimeout=1800
spring.datasource.druid.logAbandoned=true
#Druid监控配置
spring.datasource.druid.url-mapping=/druid/*
spring.datasource.druid.loginUsername=Druid监控登录用户名
spring.datasource.druid.loginPassword=Druid监控登录密码


#############################
#            JPA            #
#############################
spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql=true
#############################
#          Mybatis          #
#############################


#############################
#           redis           #
#############################
spring.redis.host=主机
spring.redis.port=端口
spring.redis.database=0
spring.redis.password=密码,可以省略
spring.redis.pool.max-idle=8
spring.redis.pool.min-idle=0
spring.redis.pool.max-active=8
spring.redis.pool.max-wait=1

#############################
#            日志            #
#############################
logging.path=日志文件存放绝对路径
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss.SSS} %-4relative [%thread] %-5level %logger{50} - %msg%n
#打印mapper日志 :
logging.level.cc.chenzhihao.SpringBootDemo.dao.mapper=trace
```