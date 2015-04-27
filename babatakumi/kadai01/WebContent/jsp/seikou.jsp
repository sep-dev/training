<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	String flag = (String)request.getAttribute("flag");
    	String title = null;
    	String h1txt= null;

    	int flag2 = Integer.parseInt(flag);

    	if(flag2 == 1){
    		title = "登録成功";
    		h1txt = "登録成功しました";
    	}else if(flag2 == 2){
    		title = "更新成功";
    		h1txt = "更新成功しました";

    	}else if(flag2 == 3){
    		title = "削除成功";
    		h1txt = "削除に成功しました";

    	}
    	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp_css.css">
		<title><% out.println(title); %></title>
	</head>
	<body>

		<h1><%out.println(h1txt); %></h1>

		<form action="/kadai01/itirann" method=post>
			<input type="submit" value="一覧表示">
		</form>
	</body>
</html>