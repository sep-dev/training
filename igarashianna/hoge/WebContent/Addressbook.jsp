<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>住所録 登録画面</title>
<link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>
	<center>
		<h1>会員情報の登録</h1>

		<form method="POST" action="http://localhost:8080/hoge/Addressbook" name="from1">
		<p class = kaigyou>
			 氏名<input type="text" name="name" size="50" value="ここに氏名を入力" onFocus="cText(this)" onBlur="sText(this)" style="color:#999"/>
			 </p><p class = kaigyou>
			 住所<input type="text" name="address" size="50"value="ここに住所を入力" onFocus="cText(this)" onBlur="sText(this)" style="color:#999"/>
			 </p><p class = kaigyou>
			 電話番号<input type="text"name="tel" size="50" maxlength="14" value="ここに電話番号を入力"onFocus="cText(this)" onBlur="sText(this)" style="color:#999" />
			 </p>
			<input type="submit" value="登録" /> <input type="reset" value="リセット" />
			<input type="button" value="一覧表示" onclick="location.href='http://localhost:8080/hoge/Addresslist'" />

		</form>

		<script type="text/javascript"><!--
		function cText(obj){
			if(obj.value==obj.defaultValue){
			obj.value="";
			obj.style.color="#000";
			}
			}
			function sText(obj){
			if(obj.value==""){
			obj.value=obj.defaultValue;
			obj.style.color="#999";
			}
			}
			//--></script>
	</center>
</body>
</html>