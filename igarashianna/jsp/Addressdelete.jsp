<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>�폜�m�F���</title>
<script type="text/javascript">
	function check() {

		var flag = 0;

			window.alert('�폜���܂����I�I')
			return true;

		}
</script>
</head>
<body>
<h1>�{���ɍ폜���Ă������ł����H</h1>


<form action="post" action="Addresslist.jsp" name="from1" onsubmit="return check()">
<input type="submit" value="�폜" /><br><br>
<input type="button" value="�ꗗ�\��"onclick="location.href='Addresslist.jsp'" />
</form>
</body>
</html>