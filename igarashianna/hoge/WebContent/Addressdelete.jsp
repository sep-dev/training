<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<html>
<head>
<link rel="stylesheet" href="style.css" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除画面</title>
</head>
<body>
<center>
<h1>本当に削除してもいいですか？</h1><br><br><br><br><br>

<%


Connection con = null;
response.setContentType ("text/html; charset=UTF-8");

//リクエストパラメーラを取得
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("question");

System.out.println("test"+id);


try{

	Class.forName("com.mysql.jdbc.Driver");

	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Addresslist","root","03294163aA");




//ID数発行用のSELECT文を準備
			PreparedStatement stmt= con.prepareStatement("select * from tbAddress Where id=?");
			stmt.setString(1, id);

			//SELECTを実行し、結果表(ResultSet)を取得
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				String name=rs.getString("name");
				String address=rs.getString("address");
				String tel=rs.getString("tel");

				out.println("<p class=hako>");

			out.println("氏名:"+name);
			out.println("住所:"+address);
			out.println("電話番号"+tel);
			out.println("</p>");
			out.println("←選択されたデータ");

			}
}
			catch (ClassNotFoundException e) {
				System.out.println("データベースに接続できません" + e);
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("ドライバーのロードに失敗しました" + e);
				e.printStackTrace();
			}finally{
				//データベース切断
				if(con != null){
					try{
						con.close();
					} catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			%>
<form method="post" action="http://localhost:8080/hoge/Addressdelete" name="from1">
<input type=hidden name=question value="<%=id%>">
<br><br><br><br><br><br><br>
<input type="submit" value="削除" /><br><br>
<input type="button" value="一覧表示" onclick="location.href='http://localhost:8080/hoge/Addresslist'" />
</form>
</center>
</body>
</html>