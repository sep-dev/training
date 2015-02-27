<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="copyright" content="&copy; Kawabata. All Rights Reserved.">
<title>Entry Form</title>
<script type="text/javascript" src="../js/base.js"></script>
<link href="../css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form_area">

<form name="form" action="" method="POST">
<div class="font_big">
<p>Entry your character.</p><br>
</div>
<div class="font_nol">
<p>キャラクターの名前・性別・身長・体重を入力してください。<br>
身長・体重は数字のみ入力してください。</p>

	<table class="tbl">
	 <tr>
		<td>
	    Name&nbsp;&nbsp;
	   		<input type="text" name="name" id="name" class="form" placeholder="Name"/>
		</td>
	 	<td>
    	Gender
    			<select name="sex" id="sex" class="form">
				<option value="" selected>Select</option>
				<option value="男">男</option>
				<option value="女">女</option>
				<option value="その他">その他</option>
			</select><br/>
		</td>
	 </tr>
	 <tr>
		<td>
	    Age&nbsp;&nbsp;&nbsp;&nbsp;
	   		<input type="text" name="age" id="age" class="form" placeholder="Age"/>
		</td>
	 	<td>
    	Hand
    		<select id="hand" class="form">
				<option selected>Select</option>
				<option value="右">右</option>
				<option value="左">左</option>
				<option value="両利き">両利き</option>
				<option value="不明">不明</option>
			</select><br/>
		</td>
	 </tr>
	 <tr>
		<td>
		Height&nbsp;
			<input type="text" id="height" name="height" class="form" placeholder="Height"/><br/>
		</td>
		<td>
		Weight
			<input type="text" id="weight" name="weight" class="form" placeholder="Weight"/><br/>
		</td>
	 </tr>
	</table>
	<br>


<br><br>
<h2>この情報で登録しますか？</h2>

<input type="submit" name="submit" value="登録" class="form1" onClick="return check_form(this.form);"/>
<input type="reset" name="reset" class="form2"/>
</div>
</form>
<br><br>
<div class="font_nol">
<footer>
<p>&copy; 2015 Kawabata. All Rights Reserved.</p>
</footer>
</div>
</div>
</body>
</html>