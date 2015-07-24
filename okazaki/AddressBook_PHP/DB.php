<?php

////////データベースの呼び出し////////

	function db_connect(){
		$db_user = "root";			//ユーザー名
		$db_pass = "Kaizu126";		//パスワード
		$db_host = "127.0.0.1";		//ホスト名
		$db_name = "books";			//データベース名
		$db_type = "mysql";			//データベースの種類

		$dsn = "$db_type:host=$db_host; dbname=$db_name; charset=utf8";

		try {
			$pdo = new pdo($dsn,$db_user,$db_pass);
			$pdo -> setattribute(pdo::ATTR_ERRMODE, pdo::ERRMODE_EXCEPTION);
			$pdo -> setattribute(pdo::ATTR_EMULATE_PREPARES,false);
		} catch(pdoexception $exception) {
			die('エラー :'.$exception->getmessage());
		}
		return $pdo;
	}
?>