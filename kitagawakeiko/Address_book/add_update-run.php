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
<h3>■住所録更新結果</h3><br />
<?php
    $id=$_POST['id'];
    $name=$_POST['name'];
    $address=$_POST['address'];
    $tel=$_POST['tel'];
    $conn=mysqli_connect('localhost','root','root123');
    mysqli_select_db($conn,'address_book01');
    $sql="SELECT * FROM users WHERE id='".$id."';";
    if($result=mysqli_query($conn,$sql)){
        if(mysqli_num_rows($result)!=0){  //指定された氏名が存在するかを調べている。
        $sql="UPDATE users SET name='".$name."', address='".$address."', tel='".$tel."'  WHERE id='".$id."';";
            if($result=mysqli_query($conn,$sql)){  //mysqli_query()は、削除が成功するとTRUEを返します。
                echo $name."様の住所録を更新しました。<br />\r\n";
            }
            else{
                echo $name."様のデータを削除に失敗しました。\r\n";
            }
        }
        else{
            echo $name."様のデータは存在しません。\r\n";
        }
    }
    else{
        echo "データの抽出に失敗しました。\r\n";
    }
    mysqli_close($conn);
?>
    </div> 
　　　　　<p><INPUT type="button" value="戻る" onClick="history.back()"></p>
 	  <p><hr></p>
     <p>&copy; Copyright  by kitagawa</p>
    </footer>
    </div> 
</body>
</html>