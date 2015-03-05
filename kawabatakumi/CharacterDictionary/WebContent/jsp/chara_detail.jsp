<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Characters List.</title>
<link href="../css/list_view.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form_area">
<div class="font_nol">
<table>
<tr>
<td>
<!-- 情報の詳細テーブル -->
	<table border="1" class="text">
	<tr>
		<td rowspan="6">
		ここに画像が入る
		</td>
		<td>
		名前:
		</td>
	<tr>
		<td>
		性別:
		</td>
	</tr>
	<tr>
		<td>
		年齢:
		</td>
	<tr>
		<td>
		身長:
		</td>
	</tr>
	<tr>
		<td>
		体重:
		</td>
	</tr>
	<tr>
		<td>
		利き手:
		</td>
	</tr>
	<tr>
		<td colspan="2">
		備考<br />
		</td>
	</tr>

	</table>
</td>

<!-- 情報の編集テーブル -->
<td>
	<table border="1">
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
</td>

</tr>
</table>

</div>
</div>
</body>
</html>