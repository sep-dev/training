<%@ page language="java" contentType="text/html; charset=Windows-31J"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
   	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   	  <link rel="stylesheet" type="text/css" href="http://localhost:8080/kennsyuu3/css/touroku.css">
      <title>登録画面</title>
   </head>
   <body>
   <div align="center">

      <h1>会員情報の登録</h1>

         <form action="botan" method="post">
      <h2>氏名:<input type="text" name="name"><br>
          住所:<input type="text" name="pass"><br>
          電話番号:<input type="text" name="dennwa" maxlength="11"></h2><br>
         <h5>
         <button type="submit" >登録</button>
          <button type="reset" >リセット</button>
         </form>
         <form action="itirann.jsp" method="post">
          <button type="submit" onclick="hyou">一覧表示</button>
          </form>
          </5>

    </div>
   </body>
</html>