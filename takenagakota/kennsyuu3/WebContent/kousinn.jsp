<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.* " import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>更新画面</title>
</head>
<body>
	<h1>会員情報の更新</h1>
		<%
	Connection conn=null;
		String id=request.getParameter("id");

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

			out.print("<form action=\"botan3\" method=\"post>");
			out.print("(<input type=\"hidden\" name=\"id\" value="+id+">");

	%>
			氏名:<input type="text" name="name"><br>
			住所:<input type="text" name="pass"><br>
			電話番号:<input type="text" name="dennwa"><br>
			<input type="submit" value="更新">
			<input type="reset" value="リセット" >
			<input type="button" value="一覧表示" onclick="hyou">
		</form>
</body>
</html>