<html>
	<head>
		<title>新規登録画面</title>

		<script src="ajaxzip2/prototype.js"></script>
		<script src="ajaxzip2/ajaxzip2.js" charset="utf-8"></script>

		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="newfile.css" type="text/css">
	</head>

	<body bgcolor="#FFFFFF" text="#000000">
		<font size="20">新規登録画面</font>
		<hr width="50%">

		<form id="form" onsubmit="return false;">
			<table>
			<tr><td width="72">郵便番号：</td><td width="160">
			<input type="text" name="zip" maxlength="7" size="7"
				onkeyup="AjaxZip2.zip2addr(this,'address','address');"></td><td width="48"></td></tr>
			</table>
		</form>

		<form name="form" method="post" action="A_success.php">
			<table>
				<tr><td align="center">氏名：</td><td><input type="text" name="name"></td><th><font color="#FF0000">※必須</font><th></tr>
				<tr><td align="center">住所：</td><td><input type="text" name="address"></td><th><font color="#FF0000">※必須</font><th></tr>
				<tr><td>電話番号：</td><td><input type="number" name="tel"></td><th><font color="#FF0000">※必須</font><th></tr>
			</table>
			<br>
			<input type="submit" value="登録">
		</form>

		<form action="Add.php">
			<input type="submit" value="リセット">
		</form>

		<form action="Ichiran.php" method="post">
			<input type="submit" value="一覧">
		</form>
	</body>
</html>