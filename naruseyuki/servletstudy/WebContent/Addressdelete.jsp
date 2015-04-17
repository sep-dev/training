<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>削除ページ</title>
</head>
<body>
	<h1>本当に削除してもいいですか？</h1>

<%
	request.setCharacterEncoding("UTF-8");

	Connection conn = null;

	String id = request.getParameter("selection");

	System.out.println("id="+id);

		try{
			//JDBCドライバの読み込み
			Class.forName("com.mysql.jdbc.Driver");

			//データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaiinlist","naruse","123a");

			//ID数発行用SQL文準備
			String sqlselect = "SELECT * FROM tbaddress WHERE id =?";
			PreparedStatement pStmtS = conn.prepareStatement(sqlselect);
			pStmtS.setString(1,id);

			//SQLを実行、ID総数を取得
			ResultSet rsS = pStmtS.executeQuery();
			while(rsS.next()){

				String name =rsS.getString("name");
				String address =rsS.getString("address");
				String tel =rsS.getString("tel");

				out.println("氏名："+name);
				out.println(" 住所："+address);
				out.println(" 電話番号 "+tel);

			}
		}


		catch(SQLException e){
			System.out.println("データベースが読み込めません！"+e);
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			System.out.print("ドライバが読み込めません！"+e);
			e.printStackTrace();
		}
		finally{
			//データベース切断
			if(conn!=null){
				try{
					conn.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

%>


<font color=#c0c0c0>←選択されたデータ</font>
	<br>
	<br>

	<FORM method="POST" action="Addresslist.jsp" name="scriptform">
		氏名 <INPUT type="text" name="shimei" size="100" maxlength="100"value=""><br>
		住所 <INPUT type="text"name="address" size="100" maxlength="100" value=""><br>
		電話番号 <INPUT type="text" name="tel" size="100" maxlength="100"value=""><br>
		<br>
		<INPUT type="submit" value="削除" onclick="location.href='Addressdelete.jsp'">
		<INPUT type="button" value="一覧表示へ" onclick="location.href='Addresslist'">
		<br>
		<br>
		<br>

	</FORM>
	<br>

</body>
</html>
