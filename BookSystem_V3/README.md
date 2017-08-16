## 说明
前端、后端分离

暂时把前端放在webapp/front下

调用的时候直接进页面:[http://localhost:8081/front/html/bookList.html](http://localhost:8081/front/html/bookList.html)

### 后期实现
前端：A容器tomcat [http://localhost:8080](http://localhost:8080)

后端：B容器tomcat [http://localhost:8081](http://localhost:8081)

## 问题记录bookList.js
### 不显示数据，或者js报错
请改js中的url连接：front/js/bookList.js
### JavaScript跨域问题
在做前后端分离的时候，前台js调用后台接口报错如下
```
XMLHttpRequest cannot load http://localhost:8080/seckill/list/.
No 'Access-Control-Allow-Origin' header is present on the requested resource.
Origin 'null' is therefore not allowed access. The response had HTTP status code 404.
```
这是由于跨域的问题造成。

解决方案参考：[解决：Access-Control-Allow-Origin与跨域](http://blog.csdn.net/wo541075754/article/details/50696841)