<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>住所録</title>
<script type="text/javascript">
	function check() {

		var flag = 0;

		if (document.from1.name.value == "") {
			flag = 1;

		} else if (document.from1.address.value == "") {
			flag = 1;

		} else if (document.from1.tel.value == "") {
			flag = 1;

		}

		if (flag) {
			window.alert('登録失敗！！！！！！！！空欄を埋めてください！')
			return false;

		} else {
			window.alert('登録成功！！！！！！！')
			return true;

		}
	}
</script>

</head>

<body>
	<h1>会員情報の登録</h1>
	<form action="post" action="Addresslist.jsp" name="from1" onsubmit="return check()">
		氏名<input type="text" name="name" size="50" value="ここに氏名を入力" /><br>
		住所<input type="text" name="address" size="50" value="ここに住所を入力" /><br>
		電話番号<input type="text" name="tel" size="50" value="ここに電話番号を入力" /><br>
		<input type="submit" value="登録" /> <input type="reset" value="リセット" />
		<input type="button" value="一覧表示"
			onclick="location.href='Addresslist.jsp'" />
	</form>


</body>
</html>