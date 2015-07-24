
<HTML>
	<HEAD>
		<TITLE>登録結果画面</TITLE>
		<META http-equiv="Content-Type" content="text/html;charset=utf-8">
		<link rel="stylesheet" href="NewFile.css" type="text/css">
	</HEAD>

	<BODY>
		<FONT size="20">会員一覧画面</FONT>
		<HR width="50%">
		<br>
		<?php

		require_once ("DB.php");
		$pdo = db_connect();

		//SQL文をセット/////////////////////////////////////////////
		$sql = "SELECT * FROM tbaddress";
		$stmt = $pdo->prepare($sql);
		$stmt->execute();

		?>

		<form action="Updata.php" method="post">
		<?php
		echo "<TABLE  border='1' cellpadding='10' >";
		echo "<TR><th>↓</th>";
		echo "<Th>氏名</th>";
		echo "<Th>住所</Th>";
		echo "<Th>電話番号</Th></TR>";

		//１ループで１行データが取り出され、データが無くなるとループを抜けます。
		while ($data = $stmt->fetch()){

		    echo "<TR>";
		    echo "<TD><input type=radio value=".$data['id']." name=data checked>";
		    echo "</TD>";

			       //列１を出力//////////////
			       echo "<TD>" . $data['name']."</TD>";
			      //列２を出力//////////////
			      echo "<TD>" . $data['address']."</TD>";
			      //列３を出力//////////////
			      echo "<TD>" . $data['tel']."</TD>";
		    echo "</TR>";
		}
		echo "</TABLE>";
		?>

		<br>
		<HR width="50%">
			<input type="submit" value="更新">
		</form>

		<form action="Add.php" method="post">
			<input type="submit" value="新規登録">
		</form>
	</BODY>
</HTML>