## JWT认证
目前已经改进了相关代码

添加springMVC过滤器,每次对jwt token进行检查

JWT认证流程:
1. 用户登录成功,生成token,返回一个对象(包含token,用户名)
2. 每次请求都带上这个对象(通过js存储在电脑)
3. jwt过滤器会校验token解密之后的name是否和用户名相同,相同则放行
4. 完成(后续可能需要加上token刷新的动作)

## 前后分离
目前是全部按这个逻辑去做

前台靠ajax去调用

## 问题记录bookList.js
### 不显示数据，或者js报错
请改front/js/bookList.js中的url链接为你的链接地址
### JavaScript跨域问题
在做前后端分离的时候，前台js调用后台接口报错如下
```
XMLHttpRequest cannot load http://localhost:8080/seckill/list/.
No 'Access-Control-Allow-Origin' header is present on the requested resource.
Origin 'null' is therefore not allowed access. The response had HTTP status code 404.
```
这是由于跨域的问题造成。

解决方案参考：[解决：Access-Control-Allow-Origin与跨域](http://blog.csdn.net/wo541075754/article/details/50696841)

## 其他问题
### springMVC拦截器
1. 不识别 <mvc:exclude-mapping />标签,解决办法:
```
http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
改为3.2+
http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd
```
2. 发现了以元素 'mvc:exclude-mapping' 开头的无效内容,顺序问题-解决如下:
```
<!--拦截器-->
  <mvc:interceptors>
    <mvc:interceptor>
      <mvc:exclude-mapping path="/login"/>
      <mvc:mapping path="/**" />
      <bean class="com.hisen.filter.JWTCheckInterceptor"></bean>
    </mvc:interceptor>
  </mvc:interceptors>
  
不能mvc:exclude-mapping开头,改为

<!--拦截器-->
  <mvc:interceptors>
    <mvc:interceptor>
      <mvc:mapping path="/**" />
      <mvc:exclude-mapping path="/login"/>
      <bean class="com.hisen.filter.JWTCheckInterceptor"></bean>
    </mvc:interceptor>
  </mvc:interceptors>
```