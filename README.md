# SSM_BookSystem SSM框架基础
SSM_BookSystem ---> Hello CRUD

说明：本项目目前包含基础的CRUD

日期：2017-05-01 22:25:37

作者：hisenyuan

网站：<a href="hisen.me" target="_blank">hisen.me</a>

---

搭建过程：
---
一般idea创建工程的过程

打开idea ---> File  ---> new ---> project  ---> maven  ---> 

create from archetype ---> maven-archetype-webapp ---> 接下来一般默认即可

最后在main目录下新建java文件夹，并且mark as source root。

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
```
├── resources
│   ├── jdbc.properties
```
创建数据库配置文件，内容如下
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
```
├── resources
│   ├── mybatis-config.xml
```
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
│   └── spring
│       ├── spring-dao.xml
│       ├── spring-service.xml
│       └── spring-web.xml
```
详细内容详见：<a href="https://github.com/hisen-yuan/SSM_BookSystem/tree/master/BookSystem_V0/src/main/resources/spring" target="_blank">resources/spring/</a>

第五步：添加logback配置文件
---
logback配置比log4j要简单点，功能类似
```
├── resources
│   ├── logback.xml
```
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
第六步：创建DAO、entity
---
如下两个文件：BookDao.java、Book.java
```
│   └── com
│       └── hisen
│           ├── dao
│           │   └── BookDao.java
│           ├── entity
│           │   └── Book.java
```
详细内容：<a href="https://github.com/hisen-yuan/SSM_BookSystem/blob/master/BookSystem_V0/src/main/java/com/hisen/dao/BookDao.java" target="_blank">BookDao.java</a>

详细内容：<a href="https://github.com/hisen-yuan/SSM_BookSystem/blob/master/BookSystem_V0/src/main/java/com/hisen/entity/Book.java" target="_blank">Book.java</a>

第七步：创建mybatis mapper文件
---
在resources/mapper/目录下创建：<a href="https://github.com/hisen-yuan/SSM_BookSystem/blob/master/BookSystem_V0/src/main/resources/mapper/BookMapper.xml" target="_blank">resources/mapper/BookMapper.xml</a>

在mapper文件中需要用到**第六步**的内容

第八步：创建service
---
```
├── service
│   ├── BookService.java
│   └── impl
│       └── BookServiceImpl.java
```
具体内容：<a href="https://github.com/hisen-yuan/SSM_BookSystem/tree/master/BookSystem_V0/src/main/java/com/hisen/service" target="_blank">service</a>

第九步：创建controller
---
```
└── web
    └── BookController.java
```
具体内容：<a href="https://github.com/hisen-yuan/SSM_BookSystem/blob/master/BookSystem_V0/src/main/java/com/hisen/web/BookController.java" target="_blank">BookController.java</a>

第十步：创建jsp页面以及配置web.xml
---
```
└── webapp
    ├── index.jsp
    └── WEB-INF
        ├── jsp
        │   ├── detail.jsp
        │   └── list.jsp
        └── web.xml
```
具体内容：<a href="https://github.com/hisen-yuan/SSM_BookSystem/tree/master/BookSystem_V0/src/main/webapp" target="_blank">查看具体内容</a>