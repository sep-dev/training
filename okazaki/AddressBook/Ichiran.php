
<HTML>
	<HEAD>
		<TITLE>登録結果画面</TITLE>
	</HEAD>
	<BODY>

		<?php
		require_once ("DB.php");
		$pdo = db_connect();

		//SQL文をセット/////////////////////////////////////////////
		$quryset = mysql_query("SELECT * FROM tbaddress");
		////////////////////////////////////////////////////////////

		echo "<TABLE  border='1' >";
		echo "<TR>";
		echo "<TD>氏名";
		echo "</TD>";
		echo "<TD>住所";
		echo "</TD>";
		echo "<TD>電話番号";
		echo "</TD>";
		echo "</TR>";

		//１ループで１行データが取り出され、データが無くなるとループを抜けます。
		while ($data = mysql_fetch_array($quryset)){

		    echo "<TR>";

			       //列１を出力//////////////
			       echo "<TD>" . $data[0];
			       echo "</TD>";
			       //////////////////////////

			      //列２を出力//////////////
			      echo "<TD>" . $data[1];
			      echo "</TD>";
		        //////////////////////////
			      //列３を出力//////////////
			      echo "<TD>" . $data[2];
			      echo "</TD>";
			      //////////////////////////

		    echo "</TR>";
		}
		echo "</TABLE>";
		?>
	</BODY>
</HTML>