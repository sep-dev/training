<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Character List.</title>
  <link href="../css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="" method="POST">
<div class="form_area">
<div class="font_big">
<p>Character List.</p>
</div>
<div class="font_nol">
<p>
キャラクターの詳細は、キャラクター名を選択し詳細ボタンを押すと見ることが出来ます。
</p>
<input type="submit" name="detail" value="詳細" />
<jsp:include page="footer.jsp"></jsp:include>
</div>
</div>
</form>
</body>
</html>