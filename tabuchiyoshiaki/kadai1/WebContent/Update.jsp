<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/kadai1/css/design.css">
<script type="text/javascript">
<!--
	function AutoCheck(checkname) {
		document.getElementById(checkname).checked = true;
	}
// -->
</script>
</head>
<body>

	<%
		try {
			request.setCharacterEncoding("UTF-8");
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/kadai1", "root", "onrain14");

			String id = request.getParameter("radioid");
			System.out.println(id);
			Statement stmt = conn.createStatement();
			String sql = "select * from  tbaddress where id = " + id + ";";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
	%>
	<h1>会員情報の更新</h1>
	<br />



	<%-- レコードのNOフィールドを表示 --%>
	<%="氏名:" + rs.getString("name")%>
	<%-- レコードのNAMEフィールドを表示 --%>
	<%="住所:" + rs.getString("address")%>
	<%-- レコードのPRICEフィールドを表示 --%>
	<%="電話番号:" + rs.getString("tel")%>



	<form style="display: inline" method="post" action="Update">
		<p>
			<input type="hidden" name="id" value=<%=id%>>
		</p>
		<p>
			氏名&nbsp;<input type="text" size="40" name="name"
				value=<%=rs.getString("name")%> id="sample1" />
		</p>
		<p>
			住所&nbsp;<input type="text" size="40" name="address"
				value=<%=rs.getString("address")%> id="sample1" />
		</p>
		<p>
			電話番号&nbsp;<input type="text" size="40" name="tel"
				value=<%=rs.getString("tel")%> id="sample1" />
		</p>
		<br />
		<p>
			<button>更新</button>
		</p>
		<input type="reset" name="reset" value="リセット">
	</form>
	<form style="display: inline" action="Tablelist1.jsp">
		<input type="submit" name="tablelist" value="一覧表示">
	</form>

	<form style="display: inline" action="Delete.jsp">
		<p>
			<input type="hidden" name="id" value=<%=id%>>
		</p>
		<input type="submit" name="delete" value="削除">
	</form>
	<br />
	<%
		}

		catch (ClassNotFoundException e) {
			out.println("ClassNotFoundException:" + e.getMessage());
		}
	%>
</body>
</html>