<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

		function check(){

			// 「OK」時の処理開始 ＋ 確認ダイアログの表示
			if(window.confirm('空欄です！入力してください！！\n一覧表示画面に戻ります')){

				location.href = "http://localhost:8080/kadai1/http://localhost:8080/kadai1/Tablelist1.jsp"; 

			}
			// 「OK」時の処理終了

			// 「キャンセル」時の処理開始
			else{

				window.alert('キャンセルされました'); // 警告ダイアログを表示

			}
			// 「キャンセル」時の処理終了

		}

		// -->

// -->
</script>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
<h1>会員情報の更新</h1>
<br>
<form method="post" action="" name="form1" onSubmit="return check()">
<p>氏名&nbsp;<input type="text" size="40" value="" name="name" id="sample1" /></p>
   <p>住所&nbsp;<input type="text" size="40" value="" name="address" id="sample1" /></p>
 <p>電話番号&nbsp;<input type="text" size="40" value=""  name="tel" id="sample1" /></p>
 <br>
 <input type="submit" name="update" value="更新" onClick="check()">
 </form>
 <input type="reset" name="reset" value="リセット">
 </form>
 <form action="Tablelist1.jsp">
 <input type="submit" name="tablelist" value="一覧表示"></form>
 <br>
 
 <input type="submit" name="delete" value="削除">
 

</body>
</html>