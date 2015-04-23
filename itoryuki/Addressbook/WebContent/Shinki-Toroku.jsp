<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>登録画面</title>
		<link href="css/Sinki.css" rel="stylesheet" type="text/css" media="screen" />
	</head>
		<body>
			<h1>会員情報の登録</h1>
				<form action="TorokuSyori" method="post">
					<label>・氏名　　　<input type="text" name="name" size="19" value="" placeholder="名前を入力してください"></label><br>
					<label>・住所　　　<input type="text"name="address" size="50" value="" placeholder="住所を入力してください"></label><br>
					<label>・電話番号　<input type="text" onKeyup="this.value=this.value.replace(/[^0-9a-z]+/i,'')"name="tel" size="19" value="" placeholder="番号を入力してください。"></label><br>
					<div><input type="submit" class="button" value="登録" >
					<input type="reset" class="button" value="リセット">
					<input type="button" class="button" value="一覧表示" onclick="location.href='Toroku-Ichiran.jsp'"></div>
				</form>
		</body>
</html>