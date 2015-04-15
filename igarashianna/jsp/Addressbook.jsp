<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ZŠ˜^</title>
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
			window.alert('“o˜^Ž¸”sIIIIIIII‹ó—“‚ð–„‚ß‚Ä‚­‚¾‚³‚¢I')
			return false;

		} else {
			window.alert('“o˜^¬Œ÷IIIIIII')
			return true;

		}
	}
</script>

</head>

<body>
	<h1>‰ïˆõî•ñ‚Ì“o˜^</h1>
	<form action="post" action="Addresslist.jsp" name="from1" onsubmit="return check()">
		Ž–¼<input type="text" name="name" size="50" value="‚±‚±‚ÉŽ–¼‚ð“ü—Í" /><br>
		ZŠ<input type="text" name="address" size="50" value="‚±‚±‚ÉZŠ‚ð“ü—Í" /><br>
		“d˜b”Ô†<input type="text" name="tel" size="50" value="‚±‚±‚É“d˜b”Ô†‚ð“ü—Í" /><br>
		<input type="submit" value="“o˜^" /> <input type="reset" value="ƒŠƒZƒbƒg" />
		<input type="button" value="ˆê——•\Ž¦"
			onclick="location.href='Addresslist.jsp'" />
	</form>


</body>
</html>