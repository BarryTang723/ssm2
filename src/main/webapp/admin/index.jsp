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
    <script src="ui/layui.js"></script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">码农招聘后台</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${wUsername}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="logout.do">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">管理求职者信息</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="site-demo-active" data-type="tabAdd_check">查.删.改</a></dd>
                        <dd><a href="javascript:;" class="site-demo-active" data-type="tabAdd_add">增</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">管理企业信息</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="site-demo-active" data-type="tabAdd_checkBoss">查.删.改</a></dd>
                        <dd><a href="javascript:;" class="site-demo-active" data-type="tabAdd_addBoss">增</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;" class="site-demo-active" data-type="tabAdd_valid">审核企业信息</a></li>
                <li class="layui-nav-item"><a id="upBtn" href="javascript:;">人脸注册</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-tab" lay-filter="demo" lay-allowclose="true">
                <ul class="layui-tab-title">
                    <li class="layui-this" lay-id="11">欢迎标签</li>

                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">欢迎,管理员${wUsername}</div>
                </div>
            </div>
<%--            <div class="site-demo-button" style="margin-bottom: 0;">--%>
<%--                <button class="layui-btn site-demo-active">新增Tab项</button>--%>
<%--                <button class="layui-btn site-demo-active" data-type="tabDelete">删除：商品管理</button>--%>
<%--                <button class="layui-btn site-demo-active" data-type="tabChange">切换到：用户管理</button>--%>
<%--            </div>--%>



        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

<script>
    layui.use('element', function(){
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            tabAdd_check: function(){
                //新增一个Tab项
                path="admin/checkWorker.jsp";
                element.tabAdd('demo', {
                    title: '查询并修改求职者信息'
                    ,content: '<iframe scrolling="yes" frameborder="0" src="'+path+'" style="width:100%;height:100%"></iframe>'
                    ,id:1
                })
            }
            ,tabAdd_add: function(othis){
                path="admin/addWorker.jsp";
                element.tabAdd('demo', {
                    title: '增加求职者信息'
                    ,content: '<iframe scrolling="yes" frameborder="0" src="'+path+'" style="width:100%;height:100%"></iframe>'
                    ,id:2
                })
            }
            ,tabAdd_checkBoss: function(){
                //新增一个Tab项
                path="admin/checkBoss.jsp";
                element.tabAdd('demo', {
                    title: '查询并修改企业信息'
                    ,content: '<iframe scrolling="yes" frameborder="0" src="'+path+'" style="width:100%;height:100%"></iframe>'
                    ,id:3
                })
            }
            ,tabAdd_addBoss: function(othis){
                path="admin/addBoss.jsp";
                element.tabAdd('demo', {
                    title: '增加企业信息'
                    ,content: '<iframe scrolling="yes" frameborder="0" src="'+path+'" style="width:100%;height:100%"></iframe>'
                    ,id:4
                })
            }
            ,tabAdd_valid: function(othis){
                path="admin/validateBoss.jsp";
                element.tabAdd('demo', {
                    title: '审核企业信息'
                    ,content: '<iframe scrolling="yes" frameborder="0" src="'+path+'" style="width:100%;height:100%"></iframe>'
                    ,id:3
                })
            }
        };

        $('.site-demo-active').on('click', function(){
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });

        //Hash地址的定位
        var layid = location.hash.replace(/^#test=/, '');
        element.tabChange('test', layid);

        element.on('tab(test)', function(elem){
            location.hash = 'test='+ $(this).attr('lay-id');
        });

    });
    //上传
    layui.use(["upload"],function(){
        var upload=layui.upload;
        var upper=upload.render({
            elem:"#upBtn",
            url:"admin/upper1.do",
            done:function(res){
                if(res=="1"){
                    alert("注册成功,试试人脸登录吧");
                }else {
                    alert("注册失败,传的是人脸吗?");
                }

            },
            error:function(){
                alert("error");
            }
        });
    });
</script>
</body>
</html>