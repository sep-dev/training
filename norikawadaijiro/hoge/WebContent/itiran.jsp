<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%!
public void jspInit(){
	try{
		//JDBCドライバをロード
		Class.forName("com.mysql.jdbc.Driver");
	}catch(Exception e){
		e.printStackTrace();
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>一覧画面</title>
	</head>
	<body>
		<h1>会員一覧</h1>
		<h2>↓一つだけ選択可能</h2>
		<%
			Connection con = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        int i=1;
	        try {
	            // データベースに接続するConnectionオブジェクトの取得
	            con = DriverManager.getConnection(
	                "jdbc:mysql://localhost/kadai1",
	                "root", "385464dai");
	            // データベース操作を行うためのStatementオブジェクトの取得
	            stmt = con.createStatement();
	            // SQL()を実行して、結果を得る
	            rs = stmt.executeQuery(
	              "select * from tbAddress");

	            // 得られた結果をレコードごとに表示
	            while (rs.next()) {
		%>
				<FORM name="radio" method="POST"ACTION="Update.jsp">
				<input type="radio" name="addressbook" value=1>
                <%-- レコードのnameフィールドを表示 --%>
                <%= "氏名:"+rs.getString("name")%>
                <%-- レコードのaddressフィールドを表示 --%>
                <%= "住所:"+rs.getString("address")%>
                <%-- レコードのtelフィールドを表示 --%>
                <%= "電話番号:"+rs.getString("tel")%>

                <br>
		<%
				i++;
	            }
	    %>

	            	<input type="submit" value="更新or削除">
	            </FORM>
	            <br>
	            <FORM ACTION="Addressbook.jsp">
	            	<input type="submit" value="新規登録">
	            </FORM>

	    <%

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // データベースとの接続をクローズ
	            try { rs.close(); } catch (Exception e) {}
	            try { stmt.close(); } catch (Exception e) {}
	            try { con.close(); } catch (Exception e) {}
	        }
%>
	</body>
</html>