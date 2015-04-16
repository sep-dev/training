<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>会員情報の更新</title>
</head>


<script>

function check(){
	var flag = 0;

	if(document.scriptform.shimei.value==""){
		flag =+1;
	}
	if(document.scriptform.address.value==""){
		flag =+1;
	}
	if(document.scriptform.address.value==""){
		flag =+1;
	}

	if(flag!=0){
		window.alert("　　　　　　　　空欄です！入力してください！！！！！！！")

		return false
	}
	else{
		window.alert("　　　　　　　　更新成功！！！！！！！")
		return true
	}
}

</script>


<body>
	<h1>会員情報の更新</h1>
	<br>
	<FORM method="POST" action="Addresslist.jsp" name="scriptform" onsubmit="return check()">
		氏名 <INPUT type="text" name="shimei" size="100" maxlength="100"value=""><br>
		住所 <INPUT type="text"name="address" size="100" maxlength="100" value=""><br>
		電話番号 <INPUT type="text" name="tel" size="100" maxlength="100"value=""><br>

		<INPUT type="submit" value="更新">
		<INPUT type="reset" value="リセット">
		<INPUT type="button" value="一覧表示" onclick="location.href='Addresslist.jsp'">
		<br>
		<br>
		<INPUT type="button" value="削除" onclick="location.href='Addressdelete.jsp'">
	</FORM>
	<br>

</body>
</html>