<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.* " import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="http://localhost:8080/kennsyuu3/css/touroku.css">
	<title>更新画面</title>
</head>
<body>
<div align="center">
	<h1>会員情報の更新</h1>
		<%
	Connection conn=null;
		String id = request.getParameter("id");

	try{
		Class.forName("com.mysql.jdbc.Driver");
		 conn=DriverManager.getConnection("jdbc:mysql://localhost/zyuusyoroku","root","BACK-ON7");
		String query = "Select * from  tbaddress";

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from tbaddress where id="+(id));
		rs.next();
		out.println(rs.getString("name"));
		out.println(rs.getString("zyuusyo"));
		out.println(rs.getString("bangou"));
		conn.close();

	}catch(Exception e){
		e.printStackTrace();
		out.println("error");
	}
			out.print("<form action=\"botan3\" method=\"post\">");
			out.print("<input type=\"hidden\" name=\"id\" value="+id+">");
	%>

			氏名:<input type="text" name="simei"><br>
			住所:<input type="text" name="ie"><br>
			電話番号:<input type="text" name="tell"  maxlength="11"><br>
			<button type="submit" >更新</button>
			<button type="reset" >リセット</button>
			</form>

			<form action="SakuzyoKakuninn.jsp" method="post">
			<input type="hidden" name="id" value="<%= request.getParameter("id") %>">
			<button type="submit" >削除</button>
			</form>

			<form action="itirann.jsp" method="post">
			<button type="submit" >一覧表示</button>
			</form>
			</div>

</body>
</html>