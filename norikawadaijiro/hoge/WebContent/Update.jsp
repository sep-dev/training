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
<title>更新画面</title>
</head>
<body>
	<h1>会員情報の更新</h1>
	<%
			Connection con = null;
	        Statement stmt = null;
	        ResultSet rs = null;

	        int i=1;
	        try {
	            // データベースに接続するConnectionオブジェクトの取得
	            con = DriverManager.getConnection(
	                "jdbc:mysql://localhost/kadai1",
	                "root", "pass");
	            // データベース操作を行うためのStatementオブジェクトの取得
	            stmt = con.createStatement();

	            String ss = request.getParameter("addressbook");
	            int id =Integer.parseInt(ss);

	            // SQL()を実行して、結果を得る
	            rs = stmt.executeQuery("select * from tbAddress where id = ("+id+");");
	            rs.next();
	%>
                <%-- レコードのnameフィールドを表示 --%>
                <%= "氏名："+rs.getString("name")%>
                <%-- レコードのaddressフィールドを表示 --%>
                <%= "　住所："+rs.getString("address")%>
                <%-- レコードのtelフィールドを表示 --%>
                <%= "　電話番号："+rs.getString("tel")%>

                <FORM ACTION="Update"method="get">
                	<p>
                		<input type="hidden" name="id" value=<%=id%>>
                	</p>
					<p>
						　氏名　：<input type="text" name="name" size="50" maxlength="20" value=<%=rs.getString("name")%>>
					</p>
					<p>
						　住所　：<input type="text" name="address" size="50" maxlength="50" value=<%=rs.getString("address") %>>
					</p>
					<p>
						電話番号：<input type="text" name="tel" size="48" maxlength="20" value=<%=rs.getString("tel") %>>
					</p>
					<input type="submit" value="更新">
					<br>
					<input type="reset" value="リセット">
				</FORM>
				<FORM ACTION="itiran.jsp">
					<input type="submit" value="一覧表示">
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