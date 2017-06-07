<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/7
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath = request.getContextPath(); %>
<html>
<head>
    <title>添加图书</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="nav nav-tabs">
                <li><a href="<%=appPath%>/book/list">首页</a></li>
                <li><a href="<%=appPath%>/book/detail/1003">图书具体信息</a></li>
                <li class="active"><a href="<%=appPath%>/add.jsp">添加图书信息</a></li>
                <li class="disabled"><a href="#">信息</a></li>
            </ul>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    添加图书
                    <small>增加图书信息</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" id="add" method="post" action="<%=appPath%>/book/add" accept-charset="utf-8">
                <div class="form-group">
                    <label>图书ID</label><input class="form-control" name="bookId"/>
                </div>
                <div class="form-group">
                    <label>图书名字</label><input class="form-control" name="name"/>
                </div>
                <div class="form-group">
                    <label>图书描述</label><input class="form-control" name="detail"/>
                </div>
                <div class="form-group">
                    <label>图书数量</label><input class="form-control" name="number"/>
                </div>
                <button class="btn btn-default" id="sub">Submit</button>
            </form>
        </div>
    </div>

    <div id="warning" class="row clearfix" style="display:none;">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×
                </button>
                <strong>提醒：</strong><span id="warning-text"></span>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
  function list() {
    window.location.href="<%=appPath%>/book/list";
  }
  $('#warning').css('display', 'none');
  var frm = $('#add');
  frm.submit(function (ev) {
    $.ajax({
      type: frm.attr('method'),
      url: frm.attr('action'),
      data: frm.serialize(),
      success:function(data) {
        list();
      },
      error:function(data){
        //alert("添加失败");
      }
    });
    ev.preventDefault();
  });
</script>
</body>
</html>
