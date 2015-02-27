<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">

		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>Address book02</title>
		<meta name="description" content="">
		<meta name="author" content="root">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<link rel="stylesheet" href="./css/style.css" type="text/css"/>
	</head>
	<body lang="ja">
		<div id="wrap">
			<header>
				<h1>Address Book</h1>
			</header> 
		     <div class="box0"><hr>
		      <a href="add_top.html">*Top* </a>
			  <a href="add_about.html">*About* </a>
			  <a href="add_list.php">*All List* </a>
			  <a href="add_delete.php">*Delete* </a>
			  <a href="add_update.php">*Edit* </a>
             </div>
    <div class="box4">
   <form name="add_book_form" action="add_delete-search.php" method="post">

      <h3>■住所録登録一覧</h3>
   
    <table border="1">
　　　　<th></th>
    <th>NAME</th>
    <th>ADDRESS</th>
    <th>TEL</th>

    
<?php
//▼登録一覧表示
	$conn = mysql_connect( "localhost", "root", "root123" ); //DB接続
	if( $conn == false )
	{
		die("MySQL 接続エラー");
	}
	mysql_set_charset( "utf8" );
	mysql_select_db( "address_book01" ); //DB選択（アドレス帳）
	$sql = " SELECT * FROM users ";
	$res = mysql_query( $sql );
	if( $res == false )
	{
		die("エラーが発生しました。");
	}
	while( $row = mysql_fetch_array( $res ) )
	{
		print("<tr>");
		print("<td>　*　</td>");
		print("<td>" . $row["name"] . "</td>");
		print("<td>" . $row["address"] . "</td>");
		print("<td>" . $row["tel"] . "</td>");
		print("</tr>\n");
	}

	mysql_free_result($res);
	mysql_close();
?>
　　　</table><br>
 <p><INPUT type="button" value="戻る" onClick="history.back()"></p>
　　　　<p><hr></p>
    <footer>
     <p>&copy; Copyright  by kitagawa</p>
    </footer>
  </div>
</body>
</html>