<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>�o�^���</title>
</head>
<body>
<h1>������̓o�^</h1>
	<form method="POST" action="http://localhost:8080/hoge/Addressbook" name="from1">
		����<input type="text" name="name" size="50" value="�����Ɏ��������" /><br>
		�Z��<input type="text" name="address" size="50" value="�����ɏZ�������" /><br>
		�d�b�ԍ�<input type="text" name="tel" size="50" value="�����ɓd�b�ԍ������" /><br>
		<input type="submit" value="�o�^" />
		<input type="reset" value="���Z�b�g" />
		<input type="button" value="�ꗗ�\��" onclick="location.href='http://localhost:8080/hoge/Addresslist'" />
	</form>
</body>
</html>