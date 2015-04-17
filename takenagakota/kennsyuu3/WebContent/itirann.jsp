<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.* " import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一覧画面</title>

</head>
<body>
<h1>会員一覧</h1>
<p>↓一つだけ選択可能</p>
	<%
	Connection conn=null;

	try{
		Class.forName("com.mysql.jdbc.Driver");
		 conn=DriverManager.getConnection("jdbc:mysql://localhost/zyuusyoroku","root","BACK-ON7");
		String query = "Select * from  tbaddress";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from tbaddress");

		while(rs.next()){
			out.println("<INPUT type=\"radio\" name=\"radio\" value=\"touroku\">");
			out.println(rs.getString("name"));
			out.println(rs.getString("zyuusyo"));
			out.println(rs.getString("bangou"));
			out.print("<br>");
		}
		conn.close();

	}catch(Exception e){
		e.printStackTrace();
	}
%>
	<form method="post" action="">
	<input type="submit" value="更新OR削除">
</form>
<form method="post" action="touroku.jsp">
<input type="submit" value="新規登録">
</form>
</body>
</html>