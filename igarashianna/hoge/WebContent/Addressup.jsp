<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>�X�V���</title>
</head>
<body>
<h1>������̍X�V</h1>

	<form action="post" action="Addresslist.jsp" name="from1"
		onsubmit="return check()">
		����<input type="text" name="name" size="50" value="" /><br> �Z��<input
			type="text" name="address" size="50" value="" /><br> �d�b�ԍ�<input
			type="text" name="tel" size="50" value="" /><br> <input
			type="submit" value="�X�V" /> <input type="reset" value="���Z�b�g" /> <input
			type="button" value="�ꗗ�\��" onclick="location.href='Addresslist.jsp'" /><br>
		<br> <input type="button" value="�폜"
			onclick="location.href='Addressdelete.jsp'">
	</form>
</body>
</html>