<%--削除確認画面--%>
<%--UpdateScreen.jspから遷移してきて、削除するかどうか確認する--%>
<%--削除ボタン押下時、DataDelete.javaに遷移しデータベースを操作して削除を行う--%>
<%--一覧表示ボタン押下時、DataShow.jspに遷移し会員一覧を表示--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>削除確認画面</title>
<link rel="stylesheet" href="c_list.css" type="text/css" />
</head>
<body>

	<% request.setCharacterEncoding("utf-8");
	//前の画面から受け取ったリクエスト情報を加工
	 String str = request.getParameter("delete");
	 int index = str.indexOf(",");
	String id=str.substring(0,index);//会員のID　(データ検索に使用)
	String showData=str.substring(index+1,str.length());//ID以外の会員情報
	%>
	<div id="body">
	<p>本当に削除してもいいですか？</p><br>
	<%=showData%>
	<form action="DataDelete" method="post">
		<button type="submit" name="del" value=<%=id%>>削除</button>
	</form>
	<br>
	<form action="DataShow.jsp" method="post">
		<button type="submit" name="return" value="">一覧表示</button>
	</form>
	</div>
</body>
</html>