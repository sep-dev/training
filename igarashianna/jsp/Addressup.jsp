<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>更新画面</title>
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
			window.alert('空欄です！入力してください！！！！！！！')
			return false;

		} else {
			window.alert('更新成功！！！！！！！')
			return true;

		}
	}
</script>
</head>
<body>
	<h1>会員情報の更新</h1>

	<form action="post" action="Addresslist.jsp" name="from1"
		onsubmit="return check()">
		氏名<input type="text" name="name" size="50" value="" /><br> 住所<input
			type="text" name="address" size="50" value="" /><br> 電話番号<input
			type="text" name="tel" size="50" value="" /><br> <input
			type="submit" value="更新" /> <input type="reset" value="リセット" /> <input
			type="button" value="一覧表示" onclick="location.href='Addresslist.jsp'" /><br>
		<br> <input type="button" value="削除"
			onclick="location.href='Addressdelete.jsp'">
	</form>



</body>
</html>