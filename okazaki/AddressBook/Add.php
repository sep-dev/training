
<HTML>
	<HEAD>
		<TITLE>新規登録画面</TITLE>
		<META http-equiv="Content-Type" content="text/html;charset=utf-8">
	</HEAD>
	<BODY bgcolor="#FFFFFF" text="#000000">
		<FONT size="20">新規登録画面</FONT>

	<!-- 登録フォーム -->

		<FORM name = "form" method = "post" action = "A_success.php">
			氏名：<INPUT type = "text" name = "name"><BR>
			住所：<INPUT type = "text" name = "address"><BR>
			電話番号<INPUT type = "text" name = "tel"><BR>
			<INPUT type = "submit" value = "登録">
			<input type="reset" value="リセット">
		</FORM>
		<form action="Ichiran.php" method="post">
			<input type="submit" value="一覧">
		</form>
	</BODY>
</HTML>