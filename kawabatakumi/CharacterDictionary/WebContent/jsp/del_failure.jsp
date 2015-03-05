<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error!</title>
<link href="../css/result.css" rel="stylesheet" type="text/css">
</head>
<body>

<form name="form" action="" method="POST">
	<div class="form_area">
		<div class="font_big">
			<p>Failure.</p>
		</div>
	<div class="font_nol">
			<p>削除失敗です。もう一度入力してください。</p>

	<div class="space"></div>

	<input type="button" name="list" value="一覧へ" class="form1"/>
	<input type="button" name="new" value="新規登録" class="form2"/>

<div class="space"></div>

<jsp:include page="footer.jsp"></jsp:include>
	</div>
	</div>
</form>
</body>
</html>