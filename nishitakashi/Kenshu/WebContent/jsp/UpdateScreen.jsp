<%--更新情報入力画面--%>
<%--DataShow.jspから遷移。更新内容を実際に入力してもらう--%>
<%--更新ボタンを押下時、DataUpdate.javaに遷移しデータベースと接続後SQL文実行--%>
<%--一覧表示ボタンを押下時、DataShow.jspに遷移--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会員情報の更新</title>
<link rel="stylesheet" href="../css/c_list.css" type="text/css" />
<SCRIPT language="JavaScript">
<!--
// 数値のみを入力可能にする
function numOnly() {
    m = String.fromCharCode(event.keyCode);
    if("0123456789\b\r".indexOf(m, 0) < 0) return false;
    return true;
}
//スペースは不可
function spaceNo() {
    m = String.fromCharCode(event.keyCode);
    if(m==" "||m=="　") return false;
    return true;
}
//-->
</SCRIPT>
</head>
<body>
<%
    // データベースへのアクセス開始
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String id=(String)request.getParameter("select_radio");
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
        <h1 id="title">会員情報更新</h1>
        <%-- 得られた結果を表示 --%>
        <p>氏名：<%= rs.getString("name")%>　住所<%= rs.getString("address")%>　電話番号：<%= rs.getString("tel")%></p>
        <%-- 更新入力フォーム --%>
        <form action="../DataUpdate" method="post">
        <div id="edittext">氏名：<input type="text" name="name" value="<%= rs.getString("name")%>" placeholder="名前を入力してください" size="50" maxlength="50" onkeyDown="return spaceNo()" required/></div>
        <div id="edittext">住所：<input type="text" name="address" value="<%= rs.getString("address")%>" placeholder="住所を入力してください" size="50" maxlength="50" onkeyDown="return spaceNo()" required/></div>
        <div id="edittext">電話番号：<input type="tel" name="tel" value="<%= rs.getString("tel")%>" placeholder="電話番号を入力してください" size="50" maxlength="11" onkeyDown="return numOnly()" required/></div>
        <div id="button"><button type="submit" name="upd" value=<%=id%>>更新</button></div>
        <div id="button"><button type="reset" name="reset" value="リセット">リセット</button></div>
        </form>
        <%-- 一覧表示ボタン --%>
        <form action="DataShow.jsp"  method="post">
        <div id="button"><button type="submit" name="show" value="一覧表示">一覧表示</button></div>
        </form>
        <%-- 削除ボタン --%>
        <form action="DeleteScreen.jsp"  method="post">
        <div id="button"><button type="submit" name="delete" value=<%=request.getParameter("select_radio")%>>削除</button></div>
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
