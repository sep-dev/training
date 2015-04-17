<%--更新情報入力画面--%>
<%--DataShow.jspから遷移。更新内容を実際に入力してもらう--%>
<%--更新ボタンを押下時、DataUpdate.javaに遷移しデータベースと接続後SQL文実行--%>
<%--一覧表示ボタンを押下時、DataShow.jspに遷移--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会員情報の更新</title>
<link rel="stylesheet" href="c_list.css" type="text/css" />
<SCRIPT language="JavaScript">
<!--
// 数値のみを入力可能にする
function numOnly() {
  m = String.fromCharCode(event.keyCode);
  if("0123456789\b\r".indexOf(m, 0) < 0) return false;
  return true;
}
//-->
</SCRIPT>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	//前の画面から受け取ったリクエスト情報を加工
	 String str = request.getParameter("select_radio");
	 int index = str.indexOf(",");
	String id=str.substring(0,index);//会員のID　(データ検索に使用)
	String showData=str.substring(index+1,str.length());//ID以外の会員情報
	%>



	<h1 id="title">会員情報更新</h1>

	<div id="body">
	<%=showData%>
	<form action="DataUpdate" method="post">
		氏名：<input type="text" name="name" value="" placeholder="名前を入力してください" size="50" maxlength="30" required><br>
		住所：<input type="text" name="adr" value="" placeholder="住所を入力してください" size="50" maxlength="30" required><br>
		電話番号：<input type="tel" name="tel" value="" placeholder="電話番号を入力してください" size="50" maxlength="10" onkeyDown="return numOnly()" required><br>
		<button type="submit" name="upd" value=<%=id%>>更新</button>
		<button type="reset" name="reset" value="リセット">リセット</button>
	</form>
	<br>
	<form action="DataShow.jsp"  method="post">
		<button type="submit" name="show" value="一覧表示">一覧表示</button>
	</form>
	<br>
	<form action="DeleteScreen.jsp"  method="post">
		<button type="submit" name="delete" value=<%=request.getParameter("select_radio")%>>削除</button>
	</form>
	</div>
</body>
</html>