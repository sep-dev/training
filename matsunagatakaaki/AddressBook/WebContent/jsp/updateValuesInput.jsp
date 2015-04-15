<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
  String ID = "id";
  String ADDRESS_DATA = "addressData";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/common.css" />
    <link rel="stylesheet" type="text/css" href="./css/update.css" />
    <title>会員情報の更新</title>
  </head>
  <body>
    <section>
      <h1 class="title">会員情報の更新</h1>
      <p><% out.print((String)request.getAttribute(ADDRESS_DATA)); %></p>
      <form action="./Update" method="POST">
        <table>
          <tr>
            <td>氏名</td>
            <td><input type="text" name="name" size="20" /></td>
          </tr>
          <tr>
            <td>住所</td>
            <td><input type="text" name="address" size="25" /></td>
          </tr>
          <tr>
            <td>電話番号</td>
            <td><input type="tel" name="tel" size="20" maxlength="10" minlength="10" pattern="^[0-9]+$" /></td>
          </tr>
        </table>
        <p>
          <input type="submit" value="更新" />
          <input type="reset" value="リセット" />
          <input type="submit" value="一覧表示" formaction="./AddressList" formmethod="GET" />
        </p>
      </form>
      <form action="./jsp/deleteConfirmation.jsp" method="POST" >
        <input type="hidden" name="id" value="<%out.print((String)request.getAttribute(ID)); %>" />
        <p><input type="submit" value="削除" /></p>
      </form>
    </section>
  </body>
</html>