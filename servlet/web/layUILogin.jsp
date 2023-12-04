<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/12/2
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用layUI的登录界面</title>
    <link rel="stylesheet" href="./css/layui.css">
</head>
<body>
<style>
    .demo-login-container{width: 500px; height: 400px; margin: 200px auto 0;}
    .demo-login-other .layui-icon{position: relative; display: inline-block; margin: 0 2px; top: 2px; font-size: 26px;}
</style>
<form class="layui-form">
    <div class="demo-login-container">
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-username"></i>
                </div>
                <input type="text" name="username" value="" lay-verify="required" placeholder="用户名" lay-reqtext="请填写用户名" autocomplete="off" class="layui-input" lay-affix="clear">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="password" name="password" value="" lay-verify="required" placeholder="密   码" lay-reqtext="请填写密码" autocomplete="off" class="layui-input" lay-affix="eye">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-xs7">
                    <div class="layui-input-wrap">
                        <div class="layui-input-prefix">
                            <i class="layui-icon layui-icon-vercode"></i>
                        </div>
                        <input type="text" name="captcha" value="" lay-verify="required" placeholder="验证码" lay-reqtext="请填写验证码" autocomplete="off" class="layui-input" lay-affix="clear">
                    </div>
                </div>
                <div class="layui-col-xs5">
                    <div style="margin-left: 10px;">
                        <img src="imageCode" id="vericodeImg" onclick="changeImg()">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
            <a href="#forget" style="float: right; margin-top: 7px;">忘记密码？</a>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="demo-login">登录</button>
        </div>
        <div class="layui-form-item demo-login-other">
            <label>社交账号登录</label>
            <span style="padding: 0 21px 0 6px;">
        <a href="javascript:;"><i class="layui-icon layui-icon-login-qq" style="color: #3492ed;"></i></a>
        <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat" style="color: #4daf29;"></i></a>
        <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo" style="color: #cf1900;"></i></a>
      </span>
            或 <a href="#reg">注册帐号</a>
        </div>
    </div>
</form>
</body>
<script src="./js/layui.js"></script>
<script src="./js/jquery-3.5.1.min.js"></script>
<script>
    // 验证码
    function changeImg() {
        //需要让每次请求的url都发生变化。否则服务器会认为访问的时一张图片，就不会刷新请求了
        //每次url一样，服务器会认为访问的url是同一张图片，没变化啊
        $("#vericodeImg").attr("src","imageCode?"+Math.random())
    }

    layui.use(function(){
        var form = layui.form;
        var layer = layui.layer;
        // 提交事件
        form.on('submit(demo-login)', function(data){
            var field = data.field; // 获取表单字段值
            // // 显示填写结果，仅作演示用
            // layer.alert(JSON.stringify(field), {
            //     title: '当前填写的字段值'
            // });
            // 此处可执行 Ajax 等操作
            var stringify = JSON.stringify(field);
            $.post('/layUILogin/login',field,(res)=>{
                if (res.resp){
                    window.location.href='layUIRegister.jsp'
                    layer.alert(res.msg)
                }
            })
            return false; // 阻止默认 form 跳转
        });
    });
</script>
</html>
