<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Entry Form.</title>
<script type="text/javascript" src="../js/base.js"></script>
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
	年齢・身長・体重は数字のみ入力してください。</p>

<table class="tbl">
	 <tr>
		<td class="text">
			Name
		</td>
	 	<td class="edit">
	   		<input type="text" name="name" id="name" class="form" placeholder="Name"/>
	    	</td>
		<td class="text">
		Gender
		</td>
		<td class="edit">
   			<select name="sex" id="sex" class="form">
				<option value="" selected>Select</option>
				<option value="男">男</option>
				<option value="女">女</option>
				<option value="その他">その他</option>
			</select>
		</td>
		</tr>
		<tr>
		<td class="text">
		Age
		</td>
		<td class="edit">
			<input type="text" name="age" id="age" class="form" placeholder="Age"/>
		</td>
		<td class="text">
	    	Hand
		</td>
		<td class="edit">
	    		<select name="hand" id="hand" class="form">
				<option value="" selected>Select</option>
				<option value="右">右</option>
				<option value="左">左</option>
				<option value="両利き">両利き</option>
				<option value="不明">不明</option>
			</select>
		</td>
		</tr>
		<tr>
		<td class="text">
		Height
		</td>
		<td class="edit">
			<input type="text" id="height" name="height" class="form" placeholder="Height"/><br/>
		</td>
		<td class="text">
		Weight
		</td>
		<td class="edit">
		<input type="text" id="weight" name="weight" class="form" placeholder="Weight"/><br/>
		</td>
		</tr>
		</table>

	<div class="space"></div>

	<h2>この情報で登録しますか？</h2>

	<input type="submit" name="submit" value="登録" class="form1" onClick="return check_form(this.form);"/>
	<input type="reset" name="reset" class="form2"/>
	</div>
	</form>

	<jsp:include page="footer.jsp"></jsp:include>

	</div>
</body>
</html>