<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css" type="text/css" />
<title>会員情報の更新</title>
</head>
<body>
	<fieldset>
	<h1>会員情報の更新</h1>
	</fieldset>
	<br>

<%
	request.setCharacterEncoding("UTF-8");

	Connection conn = null;

	String boxname = null;
	String boxaddress = null;
	String boxtel = null;

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


				out.println("<p class=hekomi>");
				out.println("氏名："+name);
				out.println(" 住所："+address);
				out.println(" 電話番号 "+tel);
				out.println("</p>");

				boxname = name;
				boxaddress = address;
				boxtel = tel;
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
	　　　　　　　　　　　　　↑選択されたデータ↑
	<br>
	<br>

	<FORM method="POST" action="http://localhost:8080/servletstudy/Addressup" name="upform">
		氏名 <INPUT type="text" name="shimei" size="100" maxlength="8"value="<%=boxname%>"><br>
		住所 <INPUT type="text"name="address" size="100" maxlength="4" value="<%=boxaddress%>"><br>
		電話番号 <INPUT type="text" name="tel" size="100" maxlength="11"value="<%=boxtel%>"><br>
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
			<input type=hidden name=selectedid value="<%=sqlid%>">
			<INPUT type="submit" value="削除">
	</FORM>

	<br>

</body>
</html>