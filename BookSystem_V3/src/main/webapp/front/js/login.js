/**
 * Created by hisen on 17-8-26.
 */
var frm = $('#form');
frm.submit(function (ev) {
  $.ajax({
    type: "post",
    url: "http://localhost:8183/login",
    data: frm.serialize(),
    success:function(data) {
      console.log(data)
      var json = jQuery.parseJSON(data);
      var jwt = json.jwt;
      // 登录成功,存储令牌到本地
      localStorage["jwt"] = jwt;
      localStorage["name"] = json.name;
      console.log(jwt);
    },
    error:function(data){
      console.log(data)
    }
  });
  ev.preventDefault();
});

