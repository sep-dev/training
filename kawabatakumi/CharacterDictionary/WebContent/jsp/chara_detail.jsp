<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Content-Script-Type" content="text/javascript">
  <title>Characters List.</title>
  <link href="../css/list_view.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="../js/count.js"></script>
</head>
<body>
  <div class="form_area">
    <div class="font_nol">
<div Class="DataTable">
<div>
<div class="title_tr">
<div class="menu_cell">Character Profile.</div>
</div>
</div>
<div>
<div class="img_tr">
<div class="img_cell"><img src="../img/sample.jpg" alt="サンプル" /></div>
</div>
</div>
<div class="data_flo">
<div class="data_tr">
<div class="item_cell">名前</div>
</div>
<div class="data_tr">
<div class="item_cell">性別</div>
</div>

<div class="data_tr">
<div class="item_cell">年齢</div>
</div>

<div class="data_tr">
<div class="item_cell">身長</div>
</div>

<div class="data_tr">
<div class="item_cell">体重</div>
</div>

<div class="data_tr">
<div class="item_cell">利き手</div>
</div>

<div class="data_tr">
<div class="text_cell">備考</div>
</div>
</div>
</div>
    <!-- 情報の詳細テーブル
      <table border="0" class="left">
        <tr>
          <td colspan="2" class="menu">Character Profile.</td>
        </tr>
        <tr>
          <td rowspan="6" id="pict"><img src="../img/sample.jpg" alt="サンプル" /></td>
          <td class="data">　名前:あああああ</td>
        </tr>
        <tr>
          <td class="data">　性別:男</td>
        </tr>
        <tr>
          <td class="data">　年齢:23</td>
        </tr>
        <tr>
          <td class="data">　身長:172cm</td>
        </tr>
        <tr>
          <td class="data">　体重:62kg</td>
        </tr>
        <tr>
          <td class="data">　利き手:右</td>
        </tr>
        <tr>
          <td colspan="2" class="data">
            備考:<br />
            サンプルテキストサンプルテキストサンプルテキスト<br />
            サンプルテキストサンプルテキストサンプルテキスト<br />
            サンプルテキストサンプルテキストサンプルテキスト<br />
            サンプルテキストサンプルテキストサンプルテキスト<br />
          </td>
        </tr>
      </table>
    <!-- ここまで -->
    <!-- 情報の編集テーブル -->
      <table border="0" class="right">
        <tr>
          <td colspan="4" class="menu">Update of the data.</td>
        </tr>
        <tr>
          <td class="text">Name</td>
          <td class="edit">
            <input type="text" name="name" id="name" class="form" />
          </td>
          <td class="text">Gender</td>
          <td class="edit">
            <select name="sex" id="sex" class="select">
              <option value="" selected>Select</option>
              <option value="男">男</option>
              <option value="女">女</option>
              <option value="その他">その他</option>
            </select>
          </td>
        </tr>
        <tr>
          <td class="text">Age</td>
          <td class="edit">
            <input type="text" name="age" id="age" class="form" />
          </td>
          <td class="text">Hand</td>
          <td class="edit">
            <select name="hand" id="hand" class="select">
              <option value="" selected>Select</option>
              <option value="右">右</option>
              <option value="左">左</option>
              <option value="両利き">両利き</option>
              <option value="不明">不明</option>
            </select>
          </td>
        </tr>
        <tr>
          <td class="text">Height</td>
          <td class="edit">
            <input type="text" id="height" name="height" class="form" /><br>
          </td>
          <td class="text">Weight</td>
          <td class="edit">
            <input type="text" id="weight" name="weight" class="form" /><br>
          </td>
        </tr>
        <tr>
          <td>Note:</td>
          <td colspan="3"><div id="itextlength">255</div></td>
        </tr>
        <tr>
          <td colspan="4">
            <textarea name="note" onkeyup="textlength(value)" cols="51" rows="5" class="note"></textarea>
          </td>
        </tr>
      </table>
      <div class="parts_position">
        <input type="submit" name="update" value="更新" class="form1" />
        <input type="submit" name="delete" value="削除" class="form1" />
      </div>
    <!-- ここまで -->
    <!-- フッター -->
      <jsp:include page="footer.jsp"></jsp:include>
    <!-- ここまで -->
    </div>
  </div>
</body>
</html>