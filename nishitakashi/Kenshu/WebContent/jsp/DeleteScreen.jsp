<%--削除確認画面--%>
<%--UpdateScreen.jspから遷移してきて、削除するかどうか確認する--%>
<%--削除ボタン押下時、DataDelete.javaに遷移しデータベースを操作して削除を行う--%>
<%--一覧表示ボタン押下時、DataShow.jspに遷移し会員一覧を表示--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>削除確認画面</title>
<link rel="stylesheet" href="../css/c_list.css" type="text/css" />
</head>
<body>
<%
    // データベースへのアクセス開始
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String id=(String)request.getParameter("delete");
    try {
        // データベースに接続するConnectionオブジェクトの取得
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_customer",
                "root", "takashi3541");
        // データベース操作を行うためのStatementオブジェクトの取得
        stmt = con.createStatement();
        // SQL文作成
        request.setCharacterEncoding("utf-8");
        rs = stmt.executeQuery(
              "select  * from tbAddress where id="+id+";");
        rs.next();
%>
        <div id="wrap">
        <p>本当に削除してもいいですか？</p>
        <%-- 得られた結果を表示 --%>
        <p>氏名：<%= rs.getString("name")%>　住所<%= rs.getString("address")%>　電話番号：<%= rs.getString("tel")%></p>
        <form action="../DataDelete" method="post">
        <div id="button"><button type="submit" name="del" value=<%=id%>>削除</button></div>
        </form>
        <form action="DataShow.jsp" method="post">
        <div id="button"><button type="submit" name="return" value="">一覧表示</button></div>
        </form>
        </div><%--/wrap --%>
<%
    } catch (Exception e) {
        e.printStackTrace();
        System.out.print("失敗");
    } finally {
        // データベースとの接続をクローズ
        try { rs.close(); } catch (Exception e) {}
        try { stmt.close(); } catch (Exception e) {}
        try { con.close(); } catch (Exception e) {}
    }
%>
</body>
</html>
