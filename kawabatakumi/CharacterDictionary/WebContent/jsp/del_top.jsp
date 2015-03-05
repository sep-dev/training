<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete your Character.</title>
<link href="../css/result.css" rel="stylesheet" type="text/css">
</head>
<body>
<form name="form" action="" method="POST">
	<div class="form_area">
		<div class="font_big">
			<p>Delete this?</p>
		</div>
	<div class="font_nol">
			<p>このデータを本当に削除してもよろしいですか？</p>

<br><br><br>

	<input type="submit" name="delete" value="削除" class="form1"/>

<form method="POST" action="">
	<input type="button" name="new" value="一覧へ" class="form2"/>
</form>
<br><br><br><br><br><br>

<jsp:include page="footer.jsp"></jsp:include>
	</div>
	</div>
</form>
</body>
</html>