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
    request.setCharacterEncoding("utf-8");
    String id=null;
    while(res.next()){
    	id = res.getString("id");
    

%>
<form action="/kadai1/Update.jsp" method="post">
    	<input type="radio" value=<%=id%> name="radioid"  >

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
</form>
</body>
</html>