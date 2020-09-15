<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath %>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加一个企业</title>
    <link href="ui/css/layui.css" rel="stylesheet"/>
    <script type="text/javascript" src="ui/layui.js"></script>
</head>

<body>
<div class="layui-container">
    <form class="layui-form" action="" lay-filter="updateBossForm">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" required="" value="${boss.username}" lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">修改密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required="" value="${boss.password}" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" />
            </div>
            <div class="layui-form-mid layui-word-aux">
                密码的长度在6-12位
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">企业名称</label>
            <div class="layui-input-block">
                <input type="text" name="firmName" required="" value="${boss.firmName}" lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-block">
                <input type="text" name="phoneNum" required="" value="${boss.phoneNum}" lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="email" required="" value="${boss.email}" lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">行业</label>
            <div class="layui-input-block">
                <input type="text" name="education" required="" value="${boss.industry}" lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <fieldset class="layui-elem-field">
            <legend>管理企业付费模块</legend>
            <div class="layui-field-box">
                <div class="layui-inline">
                    <label class="layui-form-label">vip到期时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="vipDate" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" />
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否VIP</label>
                <div class="layui-input-block">
                    <input type="radio" name="isVip" value="1" title="是" />
                    <input type="radio" name="isVip" value="0" title="否" checked="" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否通过审核</label>
                <div class="layui-input-block">
                    <input type="radio" name="isValid" value="1" title="是" />
                    <input type="radio" name="isValid" value="0" title="否" checked="" />
                </div>
            </div>
        </fieldset>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    //Demo
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;
        var $ = layui.jquery;
        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });
        //监听提交
        form.on('submit(formDemo)', function(data){
            // layer.msg(JSON.stringify(data.field));
            // console.log(data.field);
            // ERROR编码不是utf-8会报错
            $.ajax({
                url:"admin/addBoss.do",
                contentType: "application/json;charset=UTF-8",
                method:'post',
                data:JSON.stringify(data.field),
                dataType:'text',
                success:function(res){
                    if(res==1){
                        layer.msg("提交成功!");
                    }else {
                        layer.msg("提交失败!");
                    }
                }

            })
            return false;
        });
    });
</script>
</body>
</html>