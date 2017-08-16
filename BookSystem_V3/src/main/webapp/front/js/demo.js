$(document).ready(function () {

    var thisUrl = document.URL;
    var id = thisUrl.split('=')[1];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/seckill/" + id + "/detail",
        dataType: 'json',
        global: "false",
        success: function (data) {
            var seckill = data;
            var seckillId = seckill.seckillId;
            var name = seckill.name;
            var number = seckill.number;
            var start_time = seckill.startTime;
            var start_date = new Date(start_time);
            var end_time = seckill.endTime;
            var end_date = new Date(end_time);
            var create_time = seckill.createTime;
            var create_date = new Date(create_time);
            $('.panel-title').append(name);
            // 进行页面的初始化
            seckill_m.detail.init({
                seckillId:seckillId,
                start_time:start_time,
                end_time:end_time
            });
        }
    })
});
var seckill_m = {
    Url:{
        now:'http://localhost:8080/seckill/time/now/',
        exposer:function(seckillId){
            return 'http://localhost:8080/seckill/'+seckillId+'/exposer/';
        },
        execution:function(seckillId,md5,phoneCookie){
            return 'http://localhost:8080/seckill/'+seckillId+'/'+md5+'/'+phoneCookie+'/execution'
        }
    },

    validatePhone: function (phoneNum) {
        if (phoneNum && phoneNum.length == 11 && !isNaN(phoneNum)) {
            return true;
        } else {
            return false;
        }
    },
    detail: {
        //详情页初始化
        init: function (params) {
            var userPhoneCookie = $.cookie('userPhone');
            if (!seckill_m.validatePhone(userPhoneCookie)) {
                $('#phoneModal').modal({
                    show: true,
                    keybody: false,
                    backdrop: 'static'
                });
                $('#seckillPhoneBtn').click(function(){
                    var inputPhone = $('#killPhoneKey').val();
                    if(seckill_m.validatePhone(inputPhone)){
                       $.cookie('userPhone',inputPhone,{path:'/',expires:7}); 
                       window.location.reload();
                    }else{
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号码错误！</label>').show(300);
                    }
                });
            }
            var startTime = params.start_time;
            var endTime = params.end_time;
            var seckillId = params.seckillId;
            $.get(seckill_m.Url.now,{},function(data){
                var nowTime = data.data;
                if(nowTime > endTime) {
                    //秒杀结束
                    $('#seckill-box').html('秒杀结束');

                }else if(nowTime < startTime){
                    //秒杀未开始，倒计时
                    var killTime = new Date(startTime+1000);
                    $('#seckill-box').countdown(killTime,function(event){
                        var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                        $('#seckill-box').html(format);

                    }).on('finish.countdown',function(){
                        seckill_m.handlerSeckill(seckillId,userPhoneCookie);
                    });
                }else{
                    //秒杀正在进行中
                    seckill_m.handlerSeckill(seckillId,userPhoneCookie);
                }
            })

        }
    },
    handlerSeckill:function(seckillId,phoneCookie) {
        $('#seckill-box').html('<button type="button" class="btn btn-success" id="killBtn">开始秒杀</button>');
        $.post(seckill_m.Url.exposer(seckillId),{},function(data){
            if(data.success){
                var exposer = data.data;
                if(exposer.exposed){
                    //获得秒杀接口地址
                    var md5 = exposer.md5;
                    var seckillId= exposer.seckillId;
                    var Url = seckill_m.Url.execution(seckillId,md5,phoneCookie);
                   //执行秒杀操作
                    $('#killBtn').one('click',function(){
                        $.post(Url,{},function(data){
                            $('#seckill-box').html('<span class="label label-success">'+data.data.stateInfo+'</span>');
                        });
                    });
                }
            }else{
                console.log('error'+data.error);
            }
        });

    }
};


