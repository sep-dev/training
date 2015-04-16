<%@ page language="java" contentType="text/html; charset=Windows-31J"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>登録画面</title>
   </head>
   <body>
      <h1>会員情報の登録</h1>
         <form action="botan" method="post">
          氏名:<input type="text" name="name"><br>
          住所:<input type="text" name="pass"><br>
          電話番号:<input type="text" name="dennwa"><br>
          <input type="submit" value="登録">
          <input type="reset" value="リセット">
         </form>
          <input type="button" value="一覧表示" onclick="hyou">
   </body>
</html>