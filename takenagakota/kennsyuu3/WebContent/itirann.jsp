<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.* " import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="http://localhost:8080/kennsyuu3/css/itirann.css">
<title>一覧画面</title>

</head>
<body>
<div align="center">
<h1>会員一覧</h1>
<p>↓一つだけ選択可能</p>
<p>
	<%
	Connection conn=null;

	try{
		Class.forName("com.mysql.jdbc.Driver");
		 conn=DriverManager.getConnection("jdbc:mysql://localhost/zyuusyoroku","root","BACK-ON7");
		String query = "Select * from  tbaddress";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from tbaddress");

		out.println("<form method=\"post\" action=\"kousinn.jsp\">");

		while(rs.next()){
			out.println(" <INPUT type=\"radio\" name=\"id\" value=\" " + rs.getString("id")+"\">");
			out.println(rs.getString("name"));
			out.println(rs.getString("zyuusyo"));
			out.println(rs.getString("bangou"));

			out.print("<br>");
		}
		conn.close();

	}catch(Exception e){
		e.printStackTrace();
		out.println("error");
	}
	%>
</p>

	<button type="submit"> 更新OR削除</button>>
</form>
<form method="post" action="touroku.jsp">
<button type="submit" >新規登録</button>
</form>

</div>
</body>
</html>