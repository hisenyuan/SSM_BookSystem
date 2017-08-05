通过命令行导入数据库
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
