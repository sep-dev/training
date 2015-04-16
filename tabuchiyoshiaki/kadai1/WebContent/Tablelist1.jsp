<%@ page contentType="text/html; charset=utf-8" import="java.sql.*" %>

<html>
<head>
<title>Tablelist</title>
</head>

<body>
<h1>会員一覧</h1>
<p>↓一つだけ選択可能</p><br>

<%
    Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost/kadai1",
        "root", "onrain14");
    Statement st=conn.createStatement();
    ResultSet res = st.executeQuery("select * from tbaddress");

    while(res.next()){
    	%>
    	<input type="radio" name="example" value="id">
    	
    	<% 
        out.println("<tr>");
        out.println("<td>" + "氏名:"+ res.getString("name") + "</td>");
        out.println("<td>" + "住所:" + res.getString("address") + "</td>");
        out.println("<td>" + "電話番号:" + res.getString("tel") + "</td>");
        out.println("<br>");
        out.println("</tr>");
    }
    st.close();
    conn.close();

%>
<INPUT type="submit" value="更新or削除">
</body>
</html>