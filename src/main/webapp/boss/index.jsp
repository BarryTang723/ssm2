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
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${wUsername}(${wIsValid})
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="logout.do">退出</a></li>
        </ul>
    </div>
</div>
<div class="layui-container">
  <span class="layui-breadcrumb" lay-separator="-">
      <a href="boss/updateBoss.do">维护公司信息</a>
      <a href="boss/addJob.jsp">发布招聘信息</a>
      <a href="boss/checkApply.jsp">处理申请</a>
      <a href="boss/worker-list.jsp">搜索求职者</a>
      <a href="boss/openVIP.jsp">开通vip</a>
  </span>

</div>
<script>
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function(elem){
            //console.log(elem)
            layer.msg(elem.text());
        });
    });
</script>
</body>
</html>