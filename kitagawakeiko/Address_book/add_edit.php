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
    	<h3>■登録の結果</h3>
<?php

/* 空白チェック */

    $name=$_POST['name'];
    if(empty($name)){
       die("Error：名前が空白です。");
    }

    $address=$_POST['address'];
    if(empty($address)){
       die("Error：住所が空白です。");
    }

    $tel=$_POST['tel'];
    if(empty($name)){
       die("Error：電話番号が空白です。");
    }

/*　DB接続チェック  */
    $conn = mysql_connect( "localhost", "root", "root123" ); 
    if( !$conn )
      {
	die("MySQL 接続エラー".mysql_error());
      }
    mysql_set_charset( "utf-8" );
    mysql_select_db( "address_book01" ); //DB選択（アドレス帳）
    $sql="INSERT INTO users values(NULL,'".$name."','".$address."','".$tel."');";
    $result = mysql_query($sql);
    if (!$result==false) {
        echo "以下の情報で登録しました。<br>\r\n";
        echo "氏名：".$name."<br />\r\n";
        echo "郵便番号：".$address."<br />\r\n";
        echo "住所：".$tel."<br />\r\n";
        }
        else{
        echo "データの書き込みに失敗しました。\r\n".mysql_error();
       }
    $close_db =mysql_close($conn);
?>
<br>
    </div> 
　　　　　<p><INPUT type="button" value="戻る" onClick="history.back()"></p>
 	  <p><hr></p>
     <p>&copy; Copyright  by kitagawa</p>
    </footer>
    </div> 
</body></html>