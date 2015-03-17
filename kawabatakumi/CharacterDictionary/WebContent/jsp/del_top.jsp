<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
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
        <div class="space"></div>
        <input type="submit" name="delete" value="削除" class="form1" />
        <input type="button" name="new" value="一覧へ" class="form2" />
        <div class="space"></div>
        <jsp:include page="footer.jsp"></jsp:include>
      </div>
    </div>
  </form>
</body>
</html>