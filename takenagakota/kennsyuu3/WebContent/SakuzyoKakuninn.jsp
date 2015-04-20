<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.* " import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除確認</title>
</head>
<body>
<h1>本当に削除してもよろしいでしょうか？</h1>

		<%
	Connection conn=null;
		String id =request.getParameter("id");
		System.out.println(id);

	try{
		Class.forName("com.mysql.jdbc.Driver");
		 conn=DriverManager.getConnection("jdbc:mysql://localhost/zyuusyoroku","root","BACK-ON7");
		String query = "Select * from  tbaddress where id="+(id);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		out.println(rs.getString("name"));
		out.println(rs.getString("zyuusyo"));
		out.println(rs.getString("bangou"));
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
		out.println("error");
	}
			out.print("<form action=\"botan4\" method=\"post\">");
			out.print("<input type=\"hidden\" name=\"id\" value="+id+">");
	%>
	<input type="submit" value="削除">
	</form>
	<form action="itirann.jsp" methud="post">
	<input type="submit" value="一覧表示">
	</form>
</body>
</html>