# SSM_BookSystem SSM框架基础
SSM_BookSystem   -   spring+spring mvc+mybatis+maven+idea

SSM_BookSystem ---> Hello CRUD

本项目之包含基础的CRUD

后期项目如果增加东西，我会新建module

date: 17-4-25 下午10:06

---

搭建过程：
---
一般idea创建工程的过程

打开idea ---> File  ---> new ---> project  ---> maven  ---> 

create from archetype ---> maven-archetype-webapp ---> 接下来一般默认即可

首先创建如下的目录结构即可：
```
├── java
│   └── com
│       └── hisen
│           ├── dao
│           │   └── BookDao.java
│           ├── entity
│           │   └── Book.java
│           ├── service
│           │   ├── BookService.java
│           │   └── impl
│           │       └── BookServiceImpl.java
│           └── web
│               └── BookController.java
├── resources
│   ├── jdbc.properties
│   ├── logback.xml
│   ├── mapper
│   │   └── BookMapper.xml
│   ├── mybatis-config.xml
│   └── spring
│       ├── spring-dao.xml
│       ├── spring-service.xml
│       └── spring-web.xml
└── webapp
    ├── index.jsp
    └── WEB-INF
        ├── jsp
        │   ├── detail.jsp
        │   └── list.jsp
        └── web.xml
```

第一步：添加Spring、Spring MVC、Mybatis的依赖
---

都配有详细的说明，这里不再重复

详情请看<a href="https://github.com/hisen-yuan/SSM_BookSystem/blob/master/BookSystem_V0/pom.xml" target="_blank">pom.xml</a>

第二步：添加数据库配置文件
---
在/resources目录下新建文件：jdbc.properties
```
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/booksystem?useUnicode=true&characterEncoding=utf8
jdbc.username=root
jdbc.password=hisen
```
这里需要创建一个数据库，名为：booksystem

建表语句如下：
```
CREATE TABLE `book` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `name` varchar(100) NOT NULL COMMENT '图书名称',
  `number` int(11) NOT NULL COMMENT '图书数量',
  `detail` varchar(200) NOT NULL COMMENT '图书描述'
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='图书表'
```
表结构如下：
```
+---------+--------------+------+-----+---------+----------------+
| Field   | Type         | Null | Key | Default | Extra          |
+---------+--------------+------+-----+---------+----------------+
| book_id | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| name    | varchar(100) | NO   |     | NULL    |                |
| number  | int(11)      | NO   |     | NULL    |                |
| detail  | varchar(200) | NO   |     | NULL    |                |
+---------+--------------+------+-----+---------+----------------+
```
第三步：添加mybatis配置文件
---
在resources目录下新建文件：mybatis-config.xml

内容如下：
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <!-- 配置全局属性 -->
  <settings>
    <!-- 使用jdbc的getGeneratedKeys获取数据库自增主键值 -->
    <setting name="useGeneratedKeys" value="true" />
    <!-- 使用列别名替换列名 默认:true -->
    <setting name="useColumnLabel" value="true" />
    <!-- 开启驼峰命名转换:Table{create_time} -> Entity{createTime} -->
    <setting name="mapUnderscoreToCamelCase" value="true" />
  </settings>
</configuration>
```
第四步：添加Spring配置文件
---
在resources/spring目录下新建三个文件：
```
spring-dao.xml
spring-service.xml
spring-web.xml
```
详细内容详见：<a href="https://github.com/hisen-yuan/SSM_BookSystem/tree/master/BookSystem_V0/src/main/resources/spring" target="_blank">resources/spring/</a>

第五步：添加logback配置文件
---
logback配置比log4j要简单点，功能类似

在resources文件夹下新建文件：logback.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<configuration debug="true">
  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>
  <!--开启debug日志模式，在控制台打印日志-->
  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```
