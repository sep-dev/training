<%--メイン画面--%>
<%--新規登録するための--%>
<%--登録ボタンを押下時、DataAdd,javaに遷移--%>
<%--一覧表示ボタンを押下時、DataShow.jspに遷移--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>メイン画面</title>
<link rel="stylesheet" href="../css/c_list.css" type="text/css" />
<SCRIPT language="JavaScript">
<!--
// 数値のみを入力可能にする
function numOnly() {
    m = String.fromCharCode(event.keyCode);
    if("0123456789\b\r".indexOf(m, 0) < 0) return false;
    return true;
}
//スペースは不可
function spaceNo() {
    m = String.fromCharCode(event.keyCode);
    if(m==" "||m=="　") return false;
    return true;
}
//-->
</SCRIPT>
</head>
<body>
    <div id="wrap">
    <h1 id="title">会員登録</h1>
    <form action="../DataAdd" method="post">
    <div id="edittext">氏名：<input type="text" name="name" value="" placeholder="名前を入力してください" size="50" maxlength="50" onkeyDown="return spaceNo()" required/></div>
    <div id="edittext">住所：<input type="text" name="address" value="" placeholder="住所を入力してください" size="50" maxlength="50" onkeyDown="return spaceNo()" required/></div>
    <div id="edittext">電話番号：<input type="tel" name="tel" value="" placeholder="電話番号を入力してください" maxlength="11" size="50" onkeyDown="return numOnly()" required/></div>
    <div id="button"><button type="submit" name="touroku" value="登録">登録</button></div>
    <div id="button"><button type="reset" name="reset" value="リセット">リセット</button></div>
    </form>
    <form action="DataShow.jsp"  method="post">
    <div id="button"><button type="submit" name="show" value="一覧表示">一覧表示</button></div>
    </form>
    </div><%--/wrap --%>
</body>
</html>
