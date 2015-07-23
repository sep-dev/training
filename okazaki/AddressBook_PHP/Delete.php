<html>
	<head>
		<title>削除画面</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="newfile.css" type="text/css">
	</head>

	<body bgcolor="#FFFFFF" text="#000000">
		<font size="20">削除画面</font>
		<hr width="50%">
		<br>

		<?php
			require_once ("db.php");
			$pdo = db_connect();
			$data = $_POST["data"];
			$sql = "select * from tbaddress where id=$data";
			$stmt = $pdo->prepare($sql);
			$stmt -> execute();

			echo "<table border=1>";

			while ($pso = $stmt->fetch()){
				echo "<tr><th>氏名</th><td>".$pso['name'] . "</td></tr>";
				echo "<tr><th>住所</th><td>".$pso['address'] ."</td></tr>";
				echo "<tr><th>電話番号</th><td>".$pso['tel'] ."</td></tr>" ;
			}
			echo "</table>";

			echo "<br>";
			echo"<hr width=50%>";
			echo "本当に削除してもよろしいですか？";

			echo "<form action=d_success.php method=post>";
				echo "<input type=hidden name=data value=$data>";
				echo "<input type=submit value=削除 >";
			echo "</form>";

		?>

		<form>
			<input type="button" value="戻る" onclick="history.back()">
		</form>
	</body>
</html>