<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>会員情報の更新</title>
</head>
<body>
	<h1>会員情報の更新</h1>
	<br>
<%
	request.setCharacterEncoding("UTF-8");

	Connection conn = null;

	//Addresslist.javaから選択したデータのid番号を取得
	String sqlid = request.getParameter("selection");

		try{
			//JDBCドライバの読み込み
			Class.forName("com.mysql.jdbc.Driver");

			//データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaiinlist","naruse","123a");

			//ID数発行用SQL文準備
			String sqlselect = "SELECT * FROM tbaddress WHERE id =?";
			PreparedStatement pStmtS = conn.prepareStatement(sqlselect);
			pStmtS.setString(1,sqlid);

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

	System.out.println("uxooooo"+sqlid);
%>
	<font color=#c0c0c0>←選択されたデータ</font>
	<br>
	<br>

	<FORM method="POST" action="http://localhost:8080/servletstudy/Addressup" name="upform">
		氏名 <INPUT type="text" name="shimei" size="100" maxlength="100"value=""><br>
		住所 <INPUT type="text"name="address" size="100" maxlength="100" value=""><br>
		電話番号 <INPUT type="text" name="tel" size="100" maxlength="100"value=""><br>
		<input type=hidden name=selectedid value="<%=sqlid%>">
		<br>
		<INPUT type="submit" value="更新">
		<INPUT type="reset" value="リセット">
		<INPUT type="button" value="一覧表示" onclick="location.href='Addresslist'">
		<br>
		<br>
		<br>
	</FORM>

	<FORM method="POST" action="Addressdelete.jsp" name="delform">
		<fieldset>

			<input type=hidden name=selectedid value="<%=sqlid%>">

			<INPUT type="submit" value="削除">
		</fieldset>
	</FORM>

	<br>

</body>
</html>