<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/kadai1/css/design.css">

<script type="text/javascript">
<!--

function check(){

	var flag = 0;


	// 設定開始（必須にする項目を設定してください）

	if(document.form1.name.value == ""){ // 「お名前」の入力をチェック

		flag = 1;

	}
	else if(document.form1.address.value == ""){ // 「住所」の入力をチェック
		
		flag = 1;

	}
	else if(document.form1.tel.value == ""){ // 「電話番号」の入力をチェック

		flag = 1;

	}

	// 設定終了


	if(flag){

		window.alert('登録失敗！！！！！！！\n空欄を埋めてください！\nもう一度最初から入力をお願いします'); // 入力漏れがあれば警告ダイアログを表示
		
		return false; // 送信を中止

	}
	else{

		return true; // 送信を実行

	}

}

// -->
</script>
</head>

<body>
<% request.setCharacterEncoding("UTF-8"); %>
<h1>会員情報の登録</h1>
<br>
<form method="post" action="Registration" name="form1" onSubmit="return check()">

 <p>氏名&nbsp;<input type="text" size="40" value="" placeholder="ここに氏名を入力" name="name" id="sample1" /></p>
   <p>住所&nbsp;<input type="text" size="40" value="" placeholder="ここに住所を入力" name="address" id="sample1" /></p>
 <p>電話番号&nbsp;<input type="text" size="40" value="" placeholder="ここに電話番号を入力" name="tel" id="sample1" /></p>
<button type="submit" name="登録" >登録</button>
<br>
<input type="reset" name="Submit" value="リセット">
</form>
<form action="Tablelist1.jsp">
<INPUT type="submit" value="一覧表示">
</form>
</body>
</html>