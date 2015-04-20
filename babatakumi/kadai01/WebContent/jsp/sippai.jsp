<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	String flag = (String)request.getAttribute("flag");
    	String title = null;
    	String h1txt= null;
    	String h2txt = null;
    	String path = null;
    	String b_name = null;

    	int flag2 = Integer.parseInt(flag);

    	if(flag2 == 1){
    		title = "登録失敗";
    		h1txt = "登録に失敗しました";
    		h2txt = "空欄を埋めてください";
    		path = "/kadai01/jsp/touroku.jsp";
    		b_name = "新規登録";
    	}else if(flag2 == 2){
    		title = "登録失敗";
    		h1txt = "登録に失敗しました";
    		h2txt = "電話番号は10文字です！！";
    		path = "/kadai01/jsp/touroku.jsp";
    		b_name = "新規登録";
    	}else if(flag2 == 3){
    		title = "更新失敗";
    		h1txt = "更新に失敗しました";
    		h2txt = "空欄を埋めてください";
    		path = "/kadai01/itirann";
    		b_name = "一覧";
    	}else if(flag2 == 4){
    		title = "更新失敗";
    		h1txt = "更新に失敗しました";
    		h2txt = "電話番号は10文字です！！";
    		path = "/kadai01/itirann";
    		b_name = "一覧";
    	}
    	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp_css.css">
<title><%out.println(title); %></title>
</head>
<body>

<h1><% out.println(h1txt);%></h1>

<h2><% out.println(h2txt);%></h2>

<form action= <%=path %> method=post>
<input type="submit" value=<%=b_name %>>
</form>
</body>
</html>