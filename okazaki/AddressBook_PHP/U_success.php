<html>
	<head>
		<title>登録結果画面</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8">
		<link rel="stylesheet" href="newfile.css" type="text/css">
	</head>

	<body>
		<?php
			require_once ("db.php");
			$pdo = db_connect();
			$name = $_POST['name'];
			$name = trim($name);			//半角スペースを削除
			$name = preg_replace('/^[\s　]*(.*?)[\s　]*$/u', '\1', $name);		//先頭・末尾の全角スペースは正規表現を使って置換
			$address = $_POST['address'];
			$address = trim($address);		//半角スペースを削除
			$address = preg_replace('/^[\s　]*(.*?)[\s　]*$/u', '\1', $address);//先頭・末尾の全角スペースは正規表現を使って置換
			$tel = $_POST['tel'];
			$tel = trim($tel);				//半角スペースを削除
			$tel = preg_replace('/^[\s　]*(.*?)[\s　]*$/u', '\1', $tel);		//先頭・末尾の全角スペースは正規表現を使って置換

	///////未入力が一つでもあった場合//////

			if(empty($name) || empty($address) || empty($tel)){
		?>

		<h3>空欄を埋めてください</h3>
		<hr width="30%">
		<form action="ichiran.php" method="post">
			<input type="submit" value="一覧">
		</form>

		<?php

	///////全て入力されていた場合//////

			}else{
				try{
					$pdo -> begintransaction();
					$data = $_POST["data"];
					$sql = "update tbaddress set name=:name ,address=:address ,tel=:tel where id=:id";
					$stmh = $pdo -> prepare($sql);
					$stmh -> bindvalue(':name', $_POST['name'], pdo::PARAM_STR);
					$stmh -> bindvalue(':address', $_POST['address'], pdo::PARAM_STR);
					$stmh -> bindvalue(':tel', $_POST['tel'], pdo::PARAM_INT);
					$stmh -> bindvalue(':id', $data, pdo::PARAM_INT);
					$stmh -> execute();
					$pdo -> commit();
		?>

				<h3>更新が完了しました。</h3>
				<hr width="30%">
				<form action="ichiran.php" method="post">
					<input type="submit" value="一覧">
				</form>

		<?php

				} catch (pdoexception $exception) {
					$pdo -> rollback();
					print"エラー：".$exception -> getmessage();
				}
			}
		?>
	</body>
</html>