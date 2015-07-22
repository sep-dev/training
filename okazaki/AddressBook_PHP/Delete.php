
<HTML>
	<HEAD>
		<TITLE>削除画面</TITLE>
		<META http-equiv="Content-Type" content="text/html;charset=utf-8">
		<link rel="stylesheet" href="NewFile.css" type="text/css">
	</HEAD>

	<BODY bgcolor="#FFFFFF" text="#000000">
		<FONT size="20">削除画面</FONT>
		<hr width="50%">
		<br>

		<?php
			require_once ("DB.php");
			$pdo = db_connect();
			$data=$_POST["data"];
			$sql = "SELECT * FROM tbaddress where id=$data";
			$stmt = $pdo->prepare($sql);
			$stmt->execute();

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
		echo "<form action=D_success.php method=post>";
		echo "<input type=hidden name=data value=$data>";
		echo "<input type=submit value= 削除 >";
		echo "</form>";

		?>

		<FORM>
			<INPUT type="button" value="戻る" onClick="history.back()">
		</FORM>
	</BODY>
</HTML>