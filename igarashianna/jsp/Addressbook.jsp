<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>�Z���^</title>
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
			window.alert('�o�^���s�I�I�I�I�I�I�I�I�󗓂𖄂߂Ă��������I')
			return false;

		} else {
			window.alert('�o�^�����I�I�I�I�I�I�I')
			return true;

		}
	}
</script>

</head>

<body>
	<h1>������̓o�^</h1>
	<form action="post" action="Addresslist.jsp" name="from1" onsubmit="return check()">
		����<input type="text" name="name" size="50" value="�����Ɏ��������" /><br>
		�Z��<input type="text" name="address" size="50" value="�����ɏZ�������" /><br>
		�d�b�ԍ�<input type="text" name="tel" size="50" value="�����ɓd�b�ԍ������" /><br>
		<input type="submit" value="�o�^" /> <input type="reset" value="���Z�b�g" />
		<input type="button" value="�ꗗ�\��"
			onclick="location.href='Addresslist.jsp'" />
	</form>


</body>
</html>