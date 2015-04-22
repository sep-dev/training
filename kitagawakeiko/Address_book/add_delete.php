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
   	      <h3>■登録情報の削除</h3>
      <p>▼削除したい人の名前を入れてください。</p>
      <p>・名前検索：<br>
      <input type="text" size="15" name="name" />
      <br></p>
      <p>
      <input type="submit" value="検索" />
      </form>
      </p><br>
 	    <footer>
 	</div>
 <p><INPUT type="button" value="戻る" onClick="history.back()"></p>
 	   	<p><hr></p>
     <p>&copy; Copyright  by kitagawa</p>
    </footer>
    </div>   
</body>
</html>

