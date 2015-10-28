<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
</head>
<body>
<form:form modelAttribute="formModel" >
<center>${message}・${message1}年・${msg}</center><br>
<center><h1>項目選択</h1>
<h2>国語　　　　　<input type="submit" name="" value="新規項目登録"></h2></center>
<center>① 四字熟語<br>
<input type="submit" name="lib" value="問題一覧"><br>
<input type="submit" name="insert" value="問題登録"><br>
<input type="submit" name="delete" value="項目削除"><br>
<input type="submit" name="lib1" value="解答一覧"></center><br><br>

<center>①漢字(読み)<br>
<input type="submit" name="lib" value="問題一覧"><br>
<input type="submit" name="insert" value="問題登録"><br>
<input type="submit" name="delete" value="項目削除"><br>
<input type="submit" name="lib1" value="解答一覧"></center><br><br>

<center>①俳句<br>
<input type="submit" name="lib" value="問題一覧"><br>
<input type="submit" name="insert" value="問題登録"><br>
<input type="submit" name="delete" value="項目削除"><br>
<input type="submit" name="lib1" value="解答一覧"><br><br>

<input type="submit" value="戻る"></center>
</form:form>
</body>
</html>
