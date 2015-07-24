<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=sjis" />
  <title>QUnit sample</title>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="/pathto/jquery.ajaxzip2.js"></script>
<script>
$(function(){
  $("#search").bind( "click" , function(){
    $('#zip1, #zip2').zip2addr({
      path: 'C:/Users/okazakinaritoshi/Downloads/KEN_ALL.CSV', //住所データの場所
      pref: '#pref_id',
      city: '#city',
      area: '#area',
      street: '#street',
      success: function() {
        alert('onSuccess');
      },error: function() {
        alert('onError');
      }
    });
  });
});
</script>
</head>
<body>
<div>
<p>郵便番号</p>
<input type="text" name="zip1" id="zip1" value="871"/> - <input type="text" name="zip2" id="zip2" value="0029"/>
<input type="button" value="検索" id="search" />
</div>
<div>
<p>都道府県ID</p>
  <select name="pref_id" id="pref_id">
    <option value="1">北海道</option>
    <option value="2">青森県</option>
    <option value="3">岩手県</option>
    <option value="4">宮城県</option>
    <option value="5">秋田県</option>
    <option value="6">山形県</option>
    <option value="7">福島県</option>
    <option value="8">茨城県</option>
    <option value="9">栃木県</option>
    <option value="10">群馬県</option>
    <option value="11">埼玉県</option>
    <option value="12">千葉県</option>
    <option value="13">東京都</option>
    <option value="14">神奈川県</option>
    <option value="15">新潟県</option>
    <option value="16">富山県</option>
    <option value="17">石川県</option>
    <option value="18">福井県</option>
    <option value="19">山梨県</option>
    <option value="20">長野県</option>
    <option value="21">岐阜県</option>
    <option value="22">静岡県</option>
    <option value="23">愛知県</option>
    <option value="24">三重県</option>
    <option value="25">滋賀県</option>
    <option value="26">京都府</option>
    <option value="27">大阪府</option>
    <option value="28">兵庫県</option>
    <option value="29">奈良県</option>
    <option value="30">和歌山県</option>
    <option value="31">鳥取県</option>
    <option value="32">島根県</option>
    <option value="33">岡山県</option>
    <option value="34">広島県</option>
    <option value="35">山口県</option>
    <option value="36">徳島県</option>
    <option value="37">香川県</option>
    <option value="38">愛媛県</option>
    <option value="39">高知県</option>
    <option value="40">福岡県</option>
    <option value="41">佐賀県</option>
    <option value="42">長崎県</option>
    <option value="43">熊本県</option>
    <option value="44">大分県</option>
    <option value="45">宮崎県</option>
    <option value="46">鹿児島県</option>
    <option value="47">沖縄県</option>
  </select><br/>
</div>
<div>
<p>市区町村</p>
<input type="text" name="city" id="city" />
</div>
<div>
<p>町域</p>
<input type="text" name="area" id="area" />
</div>
<div>
<p>番地</p>
<input type="text" name="street" id="street" />
</div>
</body>
</html>