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
    <title>Insert title here</title>
    <link href="ui/css/layui.css" rel="stylesheet"/>
    <script type="text/javascript" src="ui/layui.js"></script>
    <script type="text/javascript" src="ui/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="layui-container">
    <form class="layui-form" action="">
        <%--   隐藏username     --%>
        <input type="hidden" name="username" value="${worker.username}">
        <div class="layui-form-item">
            <label class="layui-form-label">修改密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required value="${worker.password}" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">密码的长度在6-12位</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="realname" required value="${worker.realname}" lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-block">
                <input type="text" name="phoneNum" required value="${worker.phoneNum}"  lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="email" required value="${worker.email}" lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学历</label>
            <div class="layui-input-block">
                <input type="text" name="education" required value="${worker.education}" lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">目标职业</label>
            <div class="layui-input-block">
                <input type="text" name="intention" required value="${worker.intention}" lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <c:if test="${worker.sex=='男'}">
                    <input type="radio" name="sex" value="男" title="男" checked>
                    <input type="radio" name="sex" value="女" title="女">
                </c:if>
                <c:if test="${worker.sex=='女'}">
                    <input type="radio" name="sex" value="男" title="男">
                    <input type="radio" name="sex" value="女" title="女"  checked>
                </c:if>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">简历</label>
            <div class="layui-input-block">
                <textarea name="resume" placeholder="请输入内容" class="layui-textarea">${worker.resume}</textarea>
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
                url:"worker/submitMyResume.do",
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