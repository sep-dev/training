<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp_css.css">

		<title>登録画面</title>

		<!--入力できる文字を数字に限定-->
		<SCRIPT language="JavaScript">
			function numOnly() {
			  m = String.fromCharCode(event.keyCode);
			  if("0123456789\b\r".indexOf(m, 0) < 0) return false;
			  return true;
			}
		</SCRIPT>
	</head>
		<body>

		<% request.setCharacterEncoding("UTF-8"); %>

			<h1>会員情報の登録</h1>



			<!--入力フォーム -->
			<form action="/kadai01/check" method="post">
			<table class="i_tb" align ="center">
					<tr><td><label for="name">氏名:</label></td>
						<td><input type="text" name="name" style="width : 300px"></td></tr>
					<tr><td><label for="address">住所:</label></td>
						<td><input type="text" name="address" style="width : 300px"></td></tr>
					<tr><td><label for="tell">電話:</label></td>
						<td><input type="text" name="tell" maxlength="10" onkeyDown="return numOnly()" style="width : 300px"></td></tr>
			</table>

				<!-- ボタン -->
				<p>
					<button type="submit" value="登録" >登録</button>
					<input type="reset" value="リセット">
				</p>
			</form>
			<form action="/kadai01/itirann" method=post>
				<p><input type="submit" value="一覧表示"></p>
			</form>
		</body>
</html>