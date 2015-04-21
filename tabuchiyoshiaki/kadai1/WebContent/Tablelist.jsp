<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>


<%!
// サーブレットのinitメソッドに相当
public void jspInit() {
    try {
        // JDBCドライバをロード
        Class.forName("com.mysql.jdbc.Driver");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tablelist</title>
</head>

<body>
<P>JSPによるデータベースのアクセス</P>
<table border='1'><tr><th>氏名</th><th>住所</th><th>電話番号</th></tr>

<%
        // データベースへのアクセス開始
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // データベースに接続するConnectionオブジェクトの取得
            con = DriverManager.getConnection(
            		 "jdbc:mysql://localhost/kadai1",
                "root", "onrain14");
            // データベース操作を行うためのStatementオブジェクトの取得
            stmt = con.createStatement();
            // SQL()を実行して、結果を得る
            rs = stmt.executeQuery(
              "select name, address, tel from fruit");

            // 得られた結果をレコードごとに表示
            while (rs.next()) {

                 out.println( "<tr>");
                out.println(" <td>" + rs.getInt("name") + "</td>");
                 out.println(" <td>" + rs.getString("address") + "</td>");
                  out.println("<td>" + rs.getInt("tel") + "</td>");
                  out.println("</tr>");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // データベースとの接続をクローズ
            try { rs.close(); } catch (Exception e) {}
            try { stmt.close(); } catch (Exception e) {}
            try { con.close(); } catch (Exception e) {}
        }
%>

        </table>
</body>
</html>