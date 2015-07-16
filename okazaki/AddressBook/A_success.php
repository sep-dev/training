
<HTML>
	<HEAD>
		<TITLE>登録結果画面</TITLE>
	</HEAD>
	<BODY>
		<?php
			require_once ("DB.php");
			$pdo = db_connect();

			$name = $_POST['name'];
			$address = $_POST['address'];
			$tel = $_POST['tel'];


	///////未入力が一つでもあった場合//////

			if(empty($name) || empty($address) || empty($tel)){
				print"空欄を埋めてください";
		?>

		<form action="Add.php" method="post">
			<input type = "submit"value="新規登録">
		</form>

		<?php

	///////全て入力されていた場合//////

			}else{
				try{
					$pdo -> beginTransaction();
					$sql = "INSERT INTO tbaddress (name,address,tel) VALUES (:name, :address, :tel)";
					$stmh = $pdo -> prepare($sql);
					$stmh -> bindValue('name', $_POST['name'], PDO::PARAM_STR);
					$stmh -> bindValue('address', $_POST['address'], PDO::PARAM_STR);
					$stmh -> bindValue('tel', $_POST['tel'], PDO::PARAM_INT);
					$stmh -> execute();
					$pdo -> commit();
					print"登録が完了しました。<br>";

				} catch (PDOException $Exception) {
					$pdo -> rollBack();
					print"エラー：".$Exception -> getMessage();
				}
			}
		?>

		<form action="Ichiran.php" method="post">
			<input type = "submit"value="一覧">
		</form>


	</BODY>
</HTML>