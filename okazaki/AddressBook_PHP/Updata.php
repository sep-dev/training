
<HTML>
	<HEAD>
		<TITLE>更新画面</TITLE>
		<script src="ajaxzip2/prototype.js"></script>
		<script src="ajaxzip2/ajaxzip2.js" charset="UTF-8"></script>
		<META http-equiv="Content-Type" content="text/html;charset=utf-8">
		<link rel="stylesheet" href="NewFile.css" type="text/css">
	</HEAD>

	<BODY>
		<FONT size="20">更新画面</FONT>
		<HR width="50%">
		<br>

		<?php
			require_once ("DB.php");
			$pdo = db_connect();
			$data=$_POST["data"];
			$sql = "SELECT * FROM tbaddress where id=$data";
			$stmt = $pdo->prepare($sql);
			$stmt->execute();

			echo "<table  border='1' cellpadding='10' >";

			while ($pso = $stmt->fetch()){
				echo "<tr><th>氏名</th><td>".$pso['name'] . "</td></tr>";
				echo "<tr><th>住所</th><td>".$pso['address'] ."</td></tr>";
				echo "<tr><th>電話番号</th><td>".$pso['tel'] ."</td></tr>" ;
			}
			echo "</table>"
		?>

		<br>
		<HR width="50%">
		<br>

		<form id="form" onsubmit="return false;">
			<table>
				<tr><td width="72">郵便番号：</td><td width="160">
				<input type="text" name="zip" maxlength="7" size="7" onKeyUp="AjaxZip2.zip2addr(this,'address','address');"></td><td width="48"></td></tr>
			</table>
		</FORM>

		<FORM name = "form" method = "post" action = "U_success.php">
			<table>
				<tr><td>氏名：</td><td><INPUT type = "text" name = "name"></td><th><font color="#FF0000">※必須</font><th></tr>
				<tr><td>住所：</td><td><INPUT type = "text" name = "address"></td><th><font color="#FF0000">※必須</font><th></tr>
				<tr><td>電話番号：</td><td><INPUT type = "text" name = "tel"></td><th><font color="#FF0000">※必須</font><th></tr>
			</table>
			<br>
			<?php
			echo "<input type=hidden name=data value=$data>";
			?>
			<INPUT type = "submit" value = "更新">
		</FORM>

		<?php
		echo "<form action=Updata.php style=display:inline; method=post>";
			echo "<input type=hidden name=data value=$data>";
			echo "<input type=submit value=リセット>";
		echo "</form>";
		?>

		<form action="Ichiran.php" style="display:inline;" method="post">
			<input type="submit" value="一覧">
		</form>
		<HR width="10%">

		<?php
		echo "<form action=Delete.php method=post>";
			echo "<input type=hidden name=data value=$data>";
			echo "<input type=submit value= 削除 >";
		echo "</form>";
		?>
	</BODY>
</HTML>