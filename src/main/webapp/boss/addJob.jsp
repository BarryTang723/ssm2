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
    <title>添加一个招聘信息</title>
    <link href="ui/css/layui.css" rel="stylesheet"/>
    <script type="text/javascript" src="ui/layui.js"></script>
</head>

<body>
<div class="layui-container">
    <form class="layui-form" action="">
        <input type="hidden" value="${bUsername}">
        <div class="layui-form-item">
            <label class="layui-form-label">工作名称</label>
            <div class="layui-input-block">
                <input type="text" name="jobName" required  lay-verify="required" placeholder="工作名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">企业名称</label>
            <div class="layui-input-inline">
                <input type="text" name="firmName" required  lay-verify="required" placeholder="企业名称" autocomplete="off" class="layui-input">
            </div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">职位要求</label>
            <div class="layui-input-block">
                <input type="text" name="jobRequire"   lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">待遇</label>
            <div class="layui-input-block">
                <input type="text" name="treatment"  lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        var $ = layui.jquery;
        //监听提交
        form.on('submit(formDemo)', function(data){
            // layer.msg(JSON.stringify(data.field));
            // console.log(data.field);
            // ERROR编码不是utf-8会报错
            $.ajax({
                url:"boss/addJob.do",
                contentType: "application/json;charset=UTF-8",
                method:'post',
                data:JSON.stringify(data.field),
                dataType:'text',
                success:function(res){
                    if(res=="1"){
                        layer.msg("提交成功!");
                    }else if(res=="0"){
                        layer.msg("您似乎没有权限,请等待管理员审核!");
                    }
                }

            })
            return false;
        });
    });
</script>
</body>


</html>