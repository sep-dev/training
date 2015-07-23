<html>
	<head>
		<title>登録結果画面</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8">
		<link rel="stylesheet" href="newfile.css" type="text/css">
	</head>

	<body>
		<font size="20">会員一覧画面</font>
		<hr width="50%">
		<br>
		<?php

		require_once ("DB.php");
		$pdo = db_connect();

		//sql文をセット/////////////////////////////////////////////
		$sql = "select * from tbaddress";
		$stmt = $pdo->prepare($sql);
		$stmt -> execute();

		?>

		<form action="updata.php" method="post">
		<?php
		echo "<table  border='1' cellpadding='10'>";
		echo "<tr><th>↓</th>";
		echo "<th>氏名</th>";
		echo "<th>住所</th>";
		echo "<th>電話番号</th></tr>";

		//１ループで１行データが取り出され、データが無くなるとループを抜けます。
		while ($data = $stmt->fetch()){

		    echo "<tr>";
		    echo "<td><input type=radio value=".$data['id']." name=data checked>";
		    echo "</td>";

			       //列１を出力//////////////
			       echo "<td>" . $data['name']."</td>";
			      //列２を出力//////////////
			      echo "<td>" . $data['address']."</td>";
			      //列３を出力//////////////
			      echo "<td>" . $data['tel']."</td>";
		    echo "</tr>";
		}
		echo "</table>";
		?>

		<br>
		<hr width="50%">
			<input type="submit" value="更新">
		</form>

		<form action="add.php" method="post">
			<input type="submit" value="新規登録">
		</form>
	</body>
</html>