<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
		<title>�o�^���</title>
	</head>
	<body>
		<h1>������̓o�^</h1>

		<form action="Insert" method="post">
			<table>
				<tr>
					<th>����</th>
					<td><input type="text" name="name" value="" placeholder="�����Ɏ��������" style="width:250px;"></td>
				</tr>
				<tr>
					<th>�Z��</th>
					<td><input type="text" name="address" value="" placeholder="�����ɏZ�������" style="width:250px;"></tr>
				<tr>
					<th>�d�b�ԍ�</th>
					<td><input type="text" name="tel" value="" placeholder="�����ɓd�b�ԍ������" maxlength="11" style="width:250px;"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="�o�^">
			<input type="reset" value="���Z�b�g">
		</form>

		<form action="ShowAll" method="get">
			<input type="submit" value="�ꗗ�\��">
		</form>
	</body>
</html>