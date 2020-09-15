<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link href="ui/css/layui.css" rel="stylesheet"/>
    <script type="text/javascript" src="ui/layui.js"></script>
    <script type="text/javascript" src="ui/jquery-1.11.1.min.js"></script>
</head>
<body>
<%--弹出表单--%>
<form class="layui-form" id="leaveNoteForm" style="display:none">
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label" >是否下一进度</label>
        <div class="layui-input-block">
            <input type="checkbox" id="switchAddProcess" lay-skin="switch" lay-text="ON|OFF" value="1" checked>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label" >留言</label>
        <div class="layui-input-block">
            <textarea id="bMessage" lay-verify="required" name="desc" placeholder="你想对求职者说什么" class="layui-textarea"></textarea>
        </div>
    </div>
</form>


<div class="layui-container">

    <table id="applyTable" lay-filter="demo"></table>
    <script type="text/html" id="bar">
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="detail">查看</button>
        <button id="applyBtn" class="layui-btn layui-btn-sm" lay-event="apply">招聘流程管理</button>
    </script>
</div>

<script>
    layui.use('form');
    //查看职业信息
    layui.use("table",function(){
        var table=layui.table;
        table.render({
            elem:"#applyTable",
            url:"boss/checkApplies.do",
            parseData:function(res){
                return {
                    code:0,
                    msg:'工作表',
                    count:res.length,
                    data:res
                };
            },
            cols:[[
                {field:"jobName",title:"名称",width:150},
                {field:"firmName",title:"企业",width:150},
                {field:"jobRequire",title:"职位要求",width:200},
                {field:"treatment",title:"待遇",width:200},
                //apply表里的东西
                {field:"applyDateString",title:"申请时间",width:200},
                {field:"uMessage",title:"申请人留言",width:200},
                {field:"bMessage",title:"公司回复",width:200},
                {field:"applyProcessString",title:"申请进度",width:200},
                {field:"",title:"操作",width:200,toolbar:"#bar"}
            ]]
        });
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            data=obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            }else if(obj.event === 'apply'){
                //点击申请按钮
                layui.use('layer',function() {
                    var layer = layui.layer;
                    var form = layui.form;
                    var $ = layui.jquery;

                    layer.open({
                        type: 1,
                        area: ['500px', '600px'],
                        title: '管理招聘流程并留言',
                        content: $("#leaveNoteForm"),
                        shade: 0,
                        btn: ['提交', '重置'],
                        btn1: function(index, layero) {
                            var switchAddProcess = $("#switchAddProcess").val();
                            var bMessage = $("#bMessage").val();
                            $.ajax({
                                url: 'boss/changeProcess.do',
                                type: 'post',
                                dataType: 'text',
                                data: {
                                    id: data.id,
                                    switchAddProcess:switchAddProcess,
                                    bMessage: bMessage
                                },

                                success: function(data) {
                                    if (data == 1) {
                                        layer.msg('申请成功');
                                        ///location.href = "login.html";
                                    } else {
                                        layer.msg('申请失败');
                                    }
                                }
                            })
                        },
                        btn2: function(index, layero) {
                            $("#leaveNoteForm")[0].reset();
                            return false;
                        },
                        cancel: function(layero, index) {
                            layer.closeAll();
                        }

                    });



                })
                // window.location.href="applyForJob.jsp?jobId="+data.id;

            }
        });
    });





</script>
</body>
</html>