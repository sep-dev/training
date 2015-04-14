<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/top.css" />
    <link rel="stylesheet" type="text/css" href="./css/common.css" />
    <title>会員情報の登録</title>
  </head>
  <body>
  <section id="inputForm">
    <h1 class="title">会員情報の登録</h1>
      <form method="post" action="./Insert">
        <table>
          <tr>
            <td>氏名</td>
            <td><input type="text" name="name" placeholder="ここに氏名を入力" size="20" required /></td>
          </tr>
          <tr>
            <td>住所</td>
            <td><input type="text" name="address" placeholder="ここに住所を入力" size="25" required /></td>
          </tr>
          <tr>
            <td>電話番号</td>
            <td><input type="tel" name="tel" placeholder="ここに電話番号を入力" minlength="10" maxlength="10" pattern="^[0-9]+$" required/></td>
          </tr>
        </table>
        <p>
          <input type="submit" value="登録" />
          <input type="reset" value="リセット" />
        </p>
      </form>
      <form action="./AddressList"><p><input type="submit" value="一覧表示" /></p></form>
    </section>
  </body>
</html>