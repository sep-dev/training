<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<html>
	<head>
		<title>�o�^���</title>
	</head>
	<body>
		<h1>������̓o�^</h1>

		<form action="Insert" method="post">
			���� <input type="text" name="name">
			<br>
			�Z�� <input type="text" name="address">
			<br>
			�d�b�ԍ� <input type="text" name="tel" maxlength="10">
			<br>
			<input type="submit" value="�o�^">
			<input type="reset" value="���Z�b�g">
		</form>
		<form action="ShowAll" method="get">
			<input type="submit" value="�ꗗ�\��">
		</form>
	</body>
</html>