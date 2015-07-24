
<HTML>
	<HEAD>
		<TITLE>登録結果画面</TITLE>
		<META http-equiv="Content-Type" content="text/html;charset=utf-8">
		<link rel="stylesheet" href="NewFile.css" type="text/css">
	</HEAD>

	<BODY>
		<?php
			require_once ("DB.php");
			$pdo = db_connect();

			try{
				$pdo -> beginTransaction();
				$data=$_POST['data'];
				$sql =  "delete from tbaddress where id=:id";
				$stmh = $pdo -> prepare($sql);
				$stmh -> bindValue(':id',$data,PDO::PARAM_INT);
				$stmh -> execute();
				$pdo -> commit();

		?>
				<h3>削除いたしました。</h3>
				<HR width="30%">
				<form action="Ichiran.php" method="post">
					<input type = "submit"value="一覧">
				</form>

		<?php

			} catch (PDOException $Exception) {
				$pdo -> rollBack();
				print"エラー：".$Exception -> getMessage();
			}
		?>
	</BODY>
</HTML>