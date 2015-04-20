<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/kadai1/css/design3.css">
<script type="text/javascript"><!--
   function AutoCheck(checkname) {
      document.getElementById(checkname).checked = true;
   }
// --></script>
</head>
<body>

<%
try{
request.setCharacterEncoding("UTF-8");
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection(
       "jdbc:mysql://localhost/kadai1",
       "root", "onrain14");


String id = request.getParameter("id");
System.out.println(id);
Statement stmt = conn.createStatement();
String sql = "select * from  tbaddress where id = " + id +";";
System.out.println(sql);
ResultSet rs = stmt.executeQuery(sql);
rs.next();


%>
<h1>本当に削除してもいいですか？</h1>
<br>



                <%-- レコードのNOフィールドを表示 --%>
          		 <%=   "氏名:"+ rs.getString("name")%>
                <%-- レコードのNAMEフィールドを表示 --%>
               <%= "住所:" + rs.getString("address")%>
                <%-- レコードのPRICEフィールドを表示 --%>
                <%= "電話番号:" + rs.getString("tel")%>


 <form method="post" action="Not_update">
 <p><input type="hidden" name="id" value=<%= id %>></p>
 <input type="submit" name="delete" value="削除">
</form>
 <br>
 <form action="Tablelist1.jsp">
 <input type="submit" name="tablelist" value="一覧表示">
 </form>
 
 <%
}

 catch (ClassNotFoundException e){
      out.println("ClassNotFoundException:" + e.getMessage());
      }
%>

<div id="bars">
    <div class="bar"></div>
    <div class="bar"></div>
    <div class="bar"></div>
    <div class="bar"></div>
    <div class="bar"></div>
    <div class="bar"></div>
    <div class="bar"></div>
    <div class="bar"></div>
    <div class="bar"></div>
    <div class="bar"></div>
</div>
</body>
</html>