# SSM_BookSystem SSM框架基础
SSM_BookSystem ---> Hello CRUD

说明：本项目目前包含基础的CRUD

日期：2017-05-01 22:25:37

作者：hisenyuan

网站：<a href="http://hisen.me" target="_blank">hisen.me</a>

预览：

<img src="http://wx3.sinaimg.cn/mw690/b2e389b6ly1ff8gro6a4vj20tr0ivju2.jpg" />

<img src="http://wx1.sinaimg.cn/mw690/b2e389b6ly1ff8grp3x4uj20ql0emdhw.jpg" />

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
jdbc.url=jdbc:mysql://127.0.0.1:3306/booksystem?useUnicode=true&characterEncoding=utf8
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
  `detail` varchar(200) NOT NULL COMMENT '图书描述',
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

创建测试类：

1. 测试基类：查看代码<a href="https://github.com/hisen-yuan/SSM_BookSystem/blob/master/BookSystem_V0/src/test/com/hisen/test/BaseTest.java" target="_blank">BaseTest.java</a>
2. BookDao：查看代码<a href="https://github.com/hisen-yuan/SSM_BookSystem/blob/master/BookSystem_V0/src/test/com/hisen/test/BookDaoTest.java" target="_blank">BookDaoTest.java</a>
3. 测试：addBook （由于没有预先准备数据，所以就先添加）
```
20:12:26.674 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@7b7fdc8] will not be managed by Spring
20:12:26.709 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.777 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着0(String), 100(Integer), 描述0(String)
20:12:26.783 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.791 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@6f3187b0]
20:12:26.792 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
20:12:26.793 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@a307a8c] was not registered for synchronization because synchronization is not active
20:12:26.793 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@2b9ed6da] will not be managed by Spring
20:12:26.793 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.794 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着1(String), 101(Integer), 描述1(String)
20:12:26.798 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.798 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@a307a8c]
20:12:26.798 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
20:12:26.799 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4b34fff9] was not registered for synchronization because synchronization is not active
20:12:26.799 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@127a7a2e] will not be managed by Spring
20:12:26.799 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.799 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着2(String), 102(Integer), 描述2(String)
20:12:26.804 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.804 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4b34fff9]
20:12:26.805 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
20:12:26.805 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@518caac3] was not registered for synchronization because synchronization is not active
20:12:26.805 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@4f74980d] will not be managed by Spring
20:12:26.805 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.805 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着3(String), 103(Integer), 描述3(String)
20:12:26.810 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.811 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@518caac3]
20:12:26.811 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
20:12:26.811 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1722011b] was not registered for synchronization because synchronization is not active
20:12:26.811 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@5b3f61ff] will not be managed by Spring
20:12:26.811 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.812 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着4(String), 104(Integer), 描述4(String)
20:12:26.816 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.817 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1722011b]
20:12:26.817 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
20:12:26.817 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@6a47b187] was not registered for synchronization because synchronization is not active
20:12:26.817 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@1ef6d34c] will not be managed by Spring
20:12:26.817 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.818 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着5(String), 105(Integer), 描述5(String)
20:12:26.822 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.823 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@6a47b187]
20:12:26.823 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
20:12:26.823 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@6fa34d52] was not registered for synchronization because synchronization is not active
20:12:26.824 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@616ac46a] will not be managed by Spring
20:12:26.825 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.825 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着6(String), 106(Integer), 描述6(String)
20:12:26.830 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.830 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@6fa34d52]
20:12:26.830 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
20:12:26.830 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1d483de4] was not registered for synchronization because synchronization is not active
20:12:26.831 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@28d18df5] will not be managed by Spring
20:12:26.831 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.832 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着7(String), 107(Integer), 描述7(String)
20:12:26.836 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.836 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1d483de4]
20:12:26.836 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
20:12:26.836 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2b175c00] was not registered for synchronization because synchronization is not active
20:12:26.836 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@1ae8bcbc] will not be managed by Spring
20:12:26.836 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.837 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着8(String), 108(Integer), 描述8(String)
20:12:26.842 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.842 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2b175c00]
20:12:26.843 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
20:12:26.843 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@35399441] was not registered for synchronization because synchronization is not active
20:12:26.843 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@6304101a] will not be managed by Spring
20:12:26.843 [main] DEBUG com.hisen.dao.BookDao.addBook - ==>  Preparing: INSERT INTO book(`book_id`, `name`, `number`,`detail`) VALUES(?, ?, ?, ?) 
20:12:26.843 [main] DEBUG com.hisen.dao.BookDao.addBook - ==> Parameters: 0(Long), 活着9(String), 109(Integer), 描述9(String)
20:12:26.848 [main] DEBUG com.hisen.dao.BookDao.addBook - <==    Updates: 1
20:12:26.848 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@35399441]
```
2. 测试:queryById
```
20:15:03.947 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@7fc4780b] will not be managed by Spring
20:15:03.972 [main] DEBUG com.hisen.dao.BookDao.queryById - ==>  Preparing: SELECT book_id, name, number, detail FROM book WHERE book_id = ? 
20:15:04.047 [main] DEBUG com.hisen.dao.BookDao.queryById - ==> Parameters: 101(Long)
20:15:04.076 [main] DEBUG com.hisen.dao.BookDao.queryById - <==      Total: 1
20:15:04.088 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@6f3187b0]
Book{bookId=101, name='活着0', number=100, detail='描述0'}
```
以此类推，就不多写了

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

1. 创建测试类：BookServiceImplTest
2. BookServiceImplTest：查看代码<a href="https://github.com/hisen-yuan/SSM_BookSystem/blob/master/BookSystem_V0/src/test/com/hisen/test/BookServiceImplTest.java" target="_blank">BookServiceImplTest.java</a>
3. 测试：getById
```
20:19:58.871 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@37fb0bed] will not be managed by Spring
20:19:58.885 [main] DEBUG com.hisen.dao.BookDao.queryById - ==>  Preparing: SELECT book_id, name, number, detail FROM book WHERE book_id = ? 
20:19:58.974 [main] DEBUG com.hisen.dao.BookDao.queryById - ==> Parameters: 101(Long)
20:19:59.004 [main] DEBUG com.hisen.dao.BookDao.queryById - <==      Total: 1
20:19:59.011 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2bec854f]
Book{bookId=101, name='活着0', number=100, detail='描述0'}
```
第九步：创建controller
---
```
└── web
    └── BookController.java
```
具体内容：<a href="https://github.com/hisen-yuan/SSM_BookSystem/blob/master/BookSystem_V0/src/main/java/com/hisen/web/BookController.java" target="_blank">BookController.java</a>

第十步：创建jsp页面以及配置web.xml
---
页面主要使用了bootstrap
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