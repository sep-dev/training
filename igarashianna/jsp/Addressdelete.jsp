<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>削除確認画面</title>
<script type="text/javascript">
	function check() {

		var flag = 0;

			window.alert('削除しました！！')
			return true;

		}
</script>
</head>
<body>
<h1>本当に削除してもいいですか？</h1>


<form action="post" action="Addresslist.jsp" name="from1" onsubmit="return check()">
<input type="submit" value="削除" /><br><br>
<input type="button" value="一覧表示"onclick="location.href='Addresslist.jsp'" />
</form>
</body>
</html>