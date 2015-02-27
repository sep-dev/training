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
  <h2>住所更新</h2> 
<?php
    $name=$_POST['name'];
    $conn=mysqli_connect('localhost','root','root123');
    mysqli_select_db($conn,'address_book01');
    $sql="SELECT * FROM users WHERE name='".$name."';";
    if($result=mysqli_query($conn,$sql)){  //情報取得する
?>
        <table border="1">
        <tr>
        <th> </th><th>氏名</th><th>住所</th><th>電話番号</th>
        </tr>
<?php
        while($row=mysqli_fetch_array($result,MYSQL_ASSOC)){
?>
            <form method="post" action="add_update-run.php">
            <tr><td><input type="hidden" name="id" value="<?php echo$row['id'] ?>" /><?php echo$row['id'] ?></td>
            <td><input type="text" name="name" value="<?php echo$row['name'] ?>" /></tb>
            <td><input type="text" name="address" value="<?php echo$row['address'] ?>" /></td>
            <td><input type="text" name="tel" value="<?php echo$row['tel'] ?>" /></td>
            <td><input type="submit" name="up" value="更新" /></td>
            </tr>
            </form>
<?php
        }
?>
        </table>
　　　　<p><INPUT type="button" value="戻る" onClick="history.back()"></p>
<?php
    }
    else{
        echo "データの抽出に失敗しました。\r\n";
    }
    mysqli_close($conn);
?>
    </div> 
 	  <p><hr></p>
     <p>&copy; Copyright  by kitagawa</p>
    </footer>
    </div> 
</body>
</html>

