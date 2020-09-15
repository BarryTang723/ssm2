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
在这查看并修改求职者信息!
<table id="workerTable" lay-filter="test"></table>
<script type="text/html" id="bar">
    <button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</button>
    <button id="applyBtn" class="layui-btn layui-btn-sm" lay-event="edit">修改</button>
</script>

</body>
<script>
    layui.use('form');
    //查看求职者信息
    layui.use("table",function(){
        var table=layui.table;
        table.render({
            elem:"#workerTable",
            url:"admin/checkWorker.do",
            parseData:function(res){
                return {
                    code:0,
                    msg:'工作表',
                    count:res.length,
                    data:res
                };
            },
            cols:[[
                {field:"username",title:"用户名",width:150},
                {field:"password",title:"密码",width:150},
                {field:"realname",title:"姓名",width:200},
                {field:"sex",title:"性别",width:200},
                {field:"phoneNum",title:"电话",width:200},
                {field:"email",title:"邮箱",width:150},
                {field:"education",title:"学历",width:150},
                {field:"intention",title:"目标职业",width:150},
                {field:"resume",title:"简历",width:150},
                {field:"",title:"操作",width:200,toolbar:"#bar"}
            ]]
        });
        table.on('tool(test)', function(obj){
            var data = obj.data;
            data=obj.data;
            if(obj.event === 'delete'){
                layer.confirm('确定删除吗?删除后不可恢复!', function(index){
                    //删除此人信息
                    // console.log(data.id);
                    // window.location.href="admin/deleteWorker.do?id="+data.id;
                    $.ajax({
                        url: 'admin/deleteWorker.do',
                        type: 'get',
                        dataType: 'text',
                        data: {
                            id: data.id
                        },

                        success: function(data) {
                            if (data == 1) {
                                layer.msg('删除成功');
                                ///location.href = "login.html";
                            } else {
                                layer.msg('删除失败');
                            }
                        }
                    })
                    layer.close(index);
                });
            }else if(obj.event === 'edit') {
                //修改worker信息
                window.location.href="admin/upateWorker.do?id="+data.id;
            }
        });
        // window.location.href="applyForJob.jsp?jobId="+data.id;
    });
</script>
</html>