<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Content-Script-Type" content="text/javascript">
  <title>Characters List.</title>
  <link href="../css/list_view.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="../js/base.js"></script>
  <script type="text/javascript" src="../js/count.js"></script>
</head>
<body>
  <div class="form_area">
    <div class="font_nol">
      <form name="form" action="" method="POST">
        <!-- 情報テーブル▼ -->
        <div class="tbl_area">
          <div class="left">
            <div class="L_position">
              <div class="L_title">
                <div class="font_big">
                Character Profile.
                </div>
              </div>
              <!-- 登録された画像が表示される▼ -->
              <div class="img"><img src="../img/sample.jpg" alt="サンプル" /></div>
              <!-- 登録された情報が表示される▼ -->
              <div class="data">
                <div class="tbl">
                  <!-- 名前▼ -->
                  <div class="L_tr">
                    <div class="L_td">
                      名前　：
                    </div>
                  </div>
                  <!-- 性別▼ -->
                  <div class="L_tr">
                    <div class="L_td">
                      性別　：
                    </div>
                  </div>
                  <!-- 年齢▼ -->
                  <div class="L_tr">
                    <div class="L_td">
                      年齢　：
                    </div>
                  </div>
                  <!-- 身長▼ -->
                  <div class="L_tr">
                    <div class="L_td">
                      身長　：
                    </div>
                  </div>
                  <!-- 体重▼ -->
                  <div class="L_tr">
                    <div class="L_td">
                      体重　：
                    </div>
                  </div>
                  <!-- 利き手▼ -->
                  <div class="L_tr">
                    <div class="L_td">
                      利き手：
                    </div>
                  </div>
                </div>
              </div>
              <!-- 備考▼ -->
              <div class="d_note">
                備考　：<br />
              </div>
            </div>
          </div>
          <!-- 更新or削除テーブル▼ -->
          <div class="right">
            <div class="R_position">
              <div class="R_title">
                <div class="font_big">
                  Update of the data.
                </div>
              </div>
              <!-- 更新フォーム▼ -->
              <div class="R_data">
                <div class="tbl">
                  <div class="R_tr">
                    <!-- 名前▼ -->
                    <div class="R_td">
                      Name
                    </div>
                    <div class="R_tdForm">
                      <input type="text" name="name" value="" id="name" class="form" />
                    </div>
                    <!-- 性別▼ -->
                    <div class="R_td">
                      Gender
                    </div>
                    <div class="R_tdForm">
                      <select name="sex" id="sex" class="select">
                        <option value="" selected>Select</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                        <option value="その他">その他</option>
                      </select>
                    </div>
                  </div>
                  <div class="R_tr">
                    <!-- 年齢▼ -->
                    <div class="R_td">
                      Age
                    </div>
                    <div class="R_tdForm">
                      <input type="text" name="age" value="" id="age" class="form" />
                    </div>
                    <!-- 利き手▼ -->
                    <div class="R_td">
                      Hand
                    </div>
                    <div class="R_tdForm">
                      <select name="hand" id="hand" class="select">
                        <option value="" selected>Select</option>
                        <option value="右">右</option>
                        <option value="左">左</option>
                        <option value="両利き">両利き</option>
                        <option value="不明">不明</option>
                      </select>
                    </div>
                  </div>
                  <div class="R_tr">
                    <!-- 身長▼ -->
                    <div class="R_td">
                      Height
                    </div>
                    <div class="R_tdForm">
                      <input type="text" name="height" value="" id="height" class="form" />
                    </div>
                    <!-- 体重▼ -->
                    <div class="R_td">
                      Weight
                    </div>
                    <div class="R_tdForm">
                      <input type="text" name="weight" value="" id="weight" class="form" />
                    </div>
                  </div>
                </div>
              </div>
              <!-- 備考▼ -->
              <div class="R_note">
                <div class="tbl">
                  <div class="R_tr">
                    <div>
                      <div class="R_td">
                        Note
                      </div>
                      <div id="itextlength">
                        255
                      </div>
                    </div>
                  </div>
                  <div class="R_tr">
                    <div class="R_textarea">
                      <textarea name="note" onkeyup="textlength(value)" cols="51" rows="5" class="note"></textarea>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="bottom">
            <input type="submit" name="update" value="更新" class="form1" onClick="return check_form(this.form);" />
            <input type="submit" name="delete" value="削除" class="form1" />
            <jsp:include page="footer.jsp"></jsp:include>
          </div>
        </div>
      </form>
    </div>
  </div>
</body>
</html>