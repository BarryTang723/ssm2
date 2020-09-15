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
开通vip,让你的发布的职位显示在前面
<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">选择vip种类</label>
<%--        <div class="layui-input-block">--%>
<%--            <input type="radio" name="vipType" value="7" title="7天" checked="">--%>
<%--            <input type="radio" name="vipType" value="30" title="30天">--%>
<%--            <input type="radio" name="vipType" value="365" title="365天">--%>
<%--        </div>--%>
        <select id="vipType" name="vipType" lay-verify="">
            <option value="0">选择vip种类</option>
            <option value="7">7天</option>
            <option value="30">30天</option>
            <option value="365">365天</option>
        </select>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
            </div>
        </div>
    </div>
</form>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        form.on('submit(formDemo)', function(data){
            $.ajax({
                url:"boss/submitVip.do",
                method:'post',
                data:{vipType:$("#vipType").val()},
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