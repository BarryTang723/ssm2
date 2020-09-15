<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
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
</head>
<body>
<%--<h3>${jobId}</h3>--%>
<%--<%=request.getParameter("jobId")%>--%>
<h3>在这申请工作!</h3>
<form action="worker/applyForJob.do" method="post">
    <input type="hidden" name="jobId" value="<%=request.getParameter("jobId")%>">
    留言<input type="text" name="uMessage">
    <input type="submit" value="提交申请">
</form>
<script type="text/javascript" src="ui/layui.js"></script>
</body>
</html>