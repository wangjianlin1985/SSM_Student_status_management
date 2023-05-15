<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/alogin.css" rel="stylesheet" type="text/css" />

<title>用户登录</title>

</head>
<body>
<form id="form1" name="form1" action="/StudentStatus/login/main.do" method="post">
	<div class="MAIN">
		<ul>
			<li class="top"></li>
			<li class="top2"></li>
			<li class="topA"></li>
			<li class="topB">
				<span> 
					<a href="" target="_blank"><img src="${pageContext.request.contextPath}/images/login/ll.png" alt="" style="" /></a>
				</span>
			</li>
			<li class="topC"></li>
			<li class="topD">
			<ul class="login ">
				<br>
				<br>
				<li><span class="left">用户名：</span> <span style=""> <input id="userName" name="userName" type="text" class="txt" value="${userName }" /> </span></li>
				<li><span class="left">密&nbsp;&nbsp;&nbsp;码：</span> <span style=""> <input id="password" name="password" type="password" class="txt" value="${password }" onkeydown= "if(event.keyCode==13)form1.submit()"/> </span></li>
			</ul>
			</li>
			<li class="topE"></li>
			<li class="middle_A"></li>
			<li class="middle_B"></li>
			<li class="middle_C"><span class="btn"> <img alt="" src="${pageContext.request.contextPath}/images/login/btnlogin.gif" onclick="javascript:document.getElementById('form1').submit()"/> </span>&nbsp;&nbsp;<span ><font color="red">${error }</font></span></li>
			<li class="middle_D"></li>
			<li class="bottom_A"></li>
			<li class="bottom_B">高校学生学籍管理系统</li>
		</ul>
	</div>
</form>
</body>
</html>