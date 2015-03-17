<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Content-Script-Type" content="text/javascript">
  <title>Entry Form.</title>
  <script type="text/javascript" src="../js/base.js"></script>
  <script type="text/javascript" src="../js/count.js"></script>
  <link href="../css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
  <div class="form_area">
    <form name="form" action="" method="POST">
    <div class="font_big">
      <p>Entry your character.</p>
    </div>
      <div class="font_nol">
        <p>キャラクターの名前・性別・年齢・・利き手・身長・体重を入力してください。<br>
        年齢・身長・体重は数字のみ入力してください。
        備考は255文字までです。</p>
    <div class="EntryTable">
      <div class="en_default">
        <!-- 名前▼ -->
        <div class="item">Name</div>
        <div class="edit">
          <input type="text" name="name" value="" id="name" class="form" />
        </div>
        <!-- 性別▼ -->
        <div class="item">Gender</div>
        <div class="edit">
          <select name="sex" id="sex" class="select">
            <option value="" selected>Select</option>
            <option value="男">男</option>
            <option value="女">女</option>
            <option value="その他">その他</option>
          </select>
        </div>
      </div>
      <div class="en_default">
        <!-- 年齢▼ -->
        <div class="item">Age</div>
        <div class="edit">
          <input type="text" name="age" value="" id="age" class="form" />
        </div>
        <!-- 利き手▼ -->
        <div class="item">Hand</div>
        <div class="edit">
          <select name="hand" id="hand" class="select">
            <option value="" selected>Select</option>
            <option value="右">右</option>
            <option value="左">左</option>
            <option value="両利き">両利き</option>
            <option value="不明">不明</option>
          </select>
        </div>
      </div>
      <div class="en_default">
        <!-- 身長▼ -->
        <div class="item">Height</div>
        <div class="edit">
          <input type="text" name="height" value="" id="height" class="form" />
        </div>
        <!-- 体重▼ -->
        <div class="item">Weight</div>
        <div class="edit">
          <input type="text" name="weight" value="" id="weight" class="form" />
        </div>
      </div>
      <div class="en_default">
        <!-- 備考▼ -->
        <div class="item">Note</div>
        <div id="itextlength">255</div>
        <div class="item"></div>
        <div class="item"></div>
      </div>
      <div>
        <div class="en_default">
          <div class="t_area">
            <textarea name="note" onkeyup="textlength(value)" cols="51" rows="5" class="note"></textarea>
          </div>
        </div>
      </div>
        <h2>この情報で登録しますか？</h2>
        <input type="submit" name="submit" value="登録" class="form1" onClick="return check_form(this.form);" />
        <input type="reset" name="reset" class="form2" />
      </div>
    </div>
    </form>
    <jsp:include page="footer.jsp"></jsp:include>
  </div>
</body>
</html>