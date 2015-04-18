<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<%
try{
request.setCharacterEncoding("UTF-8");
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection(
       "jdbc:mysql://localhost/kadai1",
       "root", "onrain14");


String id = request.getParameter("radioid");
System.out.println(id);

Statement stmt = conn.createStatement();
String sql = "select * from  tbaddress where id = " + id +";";
System.out.println(sql);
ResultSet res = stmt.executeQuery(sql);
res.next();


%>
<h1>会員情報の更新</h1>
<br>



                <%-- レコードのNOフィールドを表示 --%>
          		 <%=   "氏名:"+ res.getString("name")%>
                <%-- レコードのNAMEフィールドを表示 --%>
               <%= "住所:" + res.getString("address")%>
                <%-- レコードのPRICEフィールドを表示 --%>
                <%= "電話番号:" + res.getString("tel")%>



 <form >
<p>氏名&nbsp;<input type="text" size="40" name="name"  value=<%=  res.getString("name")%> id="sample1" /></p>
   <p>住所&nbsp;<input type="text" size="40"  name="address"  value=<%=  res.getString("address")%> id="sample1" /></p>
 <p>電話番号&nbsp;<input type="text" size="40"   name="tel"  value=<%=  res.getString("tel")%> id="sample1" /></p>
 <br>
 <p><button onclick="check()">更新</button>
<input type="reset" name="reset" value="リセット">

 </form>

 <form action="Tablelist1.jsp">
 <input type="submit" name="tablelist" value="一覧表示">
 </form>
 <input type="submit" name="delete" value="削除">

 <br>
 <%
}

 catch (ClassNotFoundException e){
      out.println("ClassNotFoundException:" + e.getMessage());
      }
%>
</body>
</html>