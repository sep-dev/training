<%@ page contentType="text/html; charset=utf-8" import="java.sql.*" %>

<html>
<head>
<title>Tablelist</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/kadai1/css/design2.css">
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
    ResultSet rs = st.executeQuery("select * from tbaddress");
    request.setCharacterEncoding("utf-8");
    String id=null;
    while(rs.next()){
    	id = rs.getString("id");
    

%>
<form action="/kadai1/Update.jsp" method="post">
    	<input type="radio" " value=<%=id%> name="radioid" checked  >
    	

    	<%
        out.println("<tr>");
        out.println("<td>" + "氏名:"+ rs.getString("name") + "</td>");
        out.println("<td>" + "住所:" + rs.getString("address") + "</td>");
        out.println("<td>" + "電話番号:" + rs.getString("tel") + "</td>");
        out.println("<br>");
        out.println("</tr>");
    }
    st.close();
    conn.close();

		%>
<INPUT type="submit" value="更新or削除">
</form>
<br>
<form action="NewFile.jsp">
<INPUT type="submit" value="新規登録">
</form>


<h1 class = 'text_fade '>会員一覧</h1>
 <h3 class = 'text_fade '>
   
 </h3>
<div></div>
<div></div>
<div></div>
</body>
</html>