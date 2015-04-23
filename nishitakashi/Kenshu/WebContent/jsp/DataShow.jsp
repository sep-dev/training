<%--会員一覧を表示する画面--%>
<%--ラジオボタンで会員を選択--%>
<%--更新or削除ボタン押下時、UpdateScreen.jspに遷移し更新削除を行う画面を表示する--%>
<%--新規登録ボタン押下時、MainScreen.jspに遷移し新規登録を行う画面を表示する--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>一覧表示</title>
<link rel="stylesheet" href="../css/c_list.css" type="text/css" />
</head>
<body>
    <div id="wrap">
    <%--会員一覧をデータベースから取得し表示する画面--%>
    <h1 id="title">会員一覧</h1>
    <P>↓1つだけ選択可能</P>
    <form action="UpdateScreen.jsp" method="post">
<%
    // データベースへのアクセス開始
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
    // データベースに接続するConnectionオブジェクトの取得
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_customer",
                "root", "takashi3541");
        // データベース操作を行うためのStatementオブジェクトの取得
        stmt = con.createStatement();
        // SQL文作成
        rs = stmt.executeQuery("select  * from tbAddress");
        request.setCharacterEncoding("utf-8");
        // 得られた結果をレコードごとに表示
        while (rs.next()) {
%>
            <div id="list">
            <%--ラジオボタン idを次の画面に渡す--%>
            <input type="radio" name="select_radio"  value=<%= rs.getInt("id")%> required/>
            氏名：<%= rs.getString("name")%>　
            住所：<%= rs.getString("address")%>　
            電話番号：<%= rs.getString("tel")%>
            </div><%--/list --%>
<%
        }
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
    <%-- 更新削除ボタン --%>
    <div id="button"><button type="submit" name="button_update">更新or削除</button></div>
    </form>
    <%-- 新規登録ボタン --%>
    <form Action="MainScreen.jsp">
    <div id="button"><button type="submit" name="new" value="newfile">新規登録</button></div>
    </form>
    </div><%--/wrap --%>
</body>
</html>
