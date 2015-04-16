<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.DatabaseLogic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>住所録システム</title>
	</head>

	<body>
		<p>会員一覧</p>

		<!-- <form action="/address/list.jsp" method="get"> -->
		<%
		DatabaseLogic dbLogic = new DatabaseLogic();
		dbLogic.connect();
		String[][] members = dbLogic.executeSQL("SELECT ID,NAME,ADDRESS,TEL FROM ADDRESS_TBL");

		for(int i = 0; members[i] != null; i++) {

			int id = Integer.parseInt(members[i][0]);
			String name = members[i][1];
			String address = members[i][2];
			String tel = members[i][3];
		%>
				<input type="radio" name="member" value="<%= id %>">
				氏名:<%= name %> 住所:<%= address %> 電話番号:<%= tel %><br>
		<%
		}
		dbLogic.disconnect();
		%>
		<!--<input type="submit" value="更新or削除">
		</form>-->

		<p><input type="button" value="新規登録" onclick="/addressBook/index.jsp"></p>

	</body>
</html>