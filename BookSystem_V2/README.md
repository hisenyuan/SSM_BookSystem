一、说明
--
由于后面的一些功能都是突发奇想

把目前了解的一些技术尽量用到里面去

可能会有点乱，但是里面都写了比较详细的注释

后期可能注释的文档会详细一点，之前没有注意到

二、通过命令行导入数据库
--
1. 查看所有的数据库
```
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| employees          |
| mysql              |
| performance_schema |
| ssm                |
| sys                |
+--------------------+
6 rows in set (0.00 sec)
```

2. 如果不存在【booksystem】则创建
```
mysql> create database booksystem;
Query OK, 1 row affected (0.00 sec)
```
3. 选择数据库
```
mysql> use booksystem;
Database changed
```

4. 设置数据库编码
```
mysql> set names utf8;
Query OK, 0 rows affected (0.00 sec)
```

5. 导入数据库文件
```
mysql> source /home/hisen/dl/hisen/booksystem.sql
```

6. 查看数据库拥有的表
```
mysql> show tables;
+----------------------+
| Tables_in_booksystem |
+----------------------+
| appointment          |
| book                 |
| user                 |
+----------------------+
3 rows in set (0.00 sec)
```

7. 完毕

三、目录结构
--
目前aop没有具体的用处

AOP可以用的地方：参数校验，错误拦截，日志记录

以后尽量少用jsp，不利于前后端分离（SOA：面向服务）
```
hisen@hisen-pc:~/IdeaProjects/SSM_BookSystem/BookSystem_V2/src$ tree
.
├── main
│   ├── java
│   │   └── com
│   │       └── hisen
│   │           ├── aop
│   │           │   ├── GetMethodInfoHandler.java
│   │           │   └── TimeHandler.java
│   │           ├── dao
│   │           │   ├── AppointmentMapper.java
│   │           │   ├── BookDao.java
│   │           │   ├── form
│   │           │   │   └── AppointmentForm.java
│   │           │   ├── RedisCache.java
│   │           │   ├── RedisCacheTransfer.java
│   │           │   ├── sql
│   │           │   │   └── booksystem.sql
│   │           │   └── UserMapper.java
│   │           ├── entity
│   │           │   ├── AppointmentExample.java
│   │           │   ├── Appointment.java
│   │           │   ├── Book.java
│   │           │   ├── UserExample.java
│   │           │   ├── User.java
│   │           │   └── UserKey.java
│   │           ├── service
│   │           │   ├── AppointmentService.java
│   │           │   ├── BookService.java
│   │           │   └── impl
│   │           │       ├── AppointmentServiceImpl.java
│   │           │       └── BookServiceImpl.java
│   │           └── web
│   │               ├── AppointmengtController.java
│   │               └── BookController.java
│   ├── resources
│   │   ├── generatorConfig.xml
│   │   ├── jdbc.properties
│   │   ├── logback.xml
│   │   ├── mapper
│   │   │   ├── AppointmentMapper.xml
│   │   │   ├── BookMapper.xml
│   │   │   └── UserMapper.xml
│   │   ├── mybatis-config.xml
│   │   ├── redis.properties
│   │   └── spring
│   │       ├── spring-aop.xml
│   │       ├── spring-dao.xml
│   │       ├── spring-service.xml
│   │       └── spring-web.xml
│   └── webapp
│       ├── add.html
│       ├── index.html
│       ├── static
│       │   └── js
│       │       ├── jquery-3.1.1.min.js
│       │       └── jquery.paginate.js
│       └── WEB-INF
│           ├── jsp
│           │   ├── detail.jsp
│           │   └── list.jsp
│           ├── templates
│           │   ├── booklist.html
│           │   ├── detail.html
│           │   ├── footer.html
│           │   ├── header.html
│           │   ├── list.html
│           │   ├── page.html
│           │   └── readingList.html
│           └── web.xml
└── test
    ├── AppointmentServiceImplTest.java
    ├── BaseTest.java
    └── UserDaoTest.java

22 directories, 50 files
```