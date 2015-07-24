<html>
	<head>
		<title>登録結果画面</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="newfile.css" type="text/css">
	</head>

	<body>
		<?php
			require_once ("db.php");
			$pdo = db_connect();

			try{
				$pdo -> begintransaction();
				$data = $_POST['data'];
				$sql = "delete from tbaddress where id=:id";
				$stmh = $pdo -> prepare($sql);
				$stmh -> bindvalue(':id',$data,pdo::PARAM_INT);
				$stmh -> execute();
				$pdo -> commit();

		?>
				<h3>削除いたしました。</h3>
				<hr width="30%">
				<form action="Ichiran.php" method="post">
					<input type="submit" value="一覧">
				</form>

		<?php

			} catch (pdoexception $exception) {
				$pdo -> rollback();
				print"エラー：".$exception -> getmessage();
			}
		?>
	</body>
</html>