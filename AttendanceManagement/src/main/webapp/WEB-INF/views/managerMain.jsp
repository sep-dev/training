<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>管理者メイン画面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
</head>
<body>
    <h1>${title}</h1>
    <p>${message}</p>
    <input type="button" onclick="location.href='studentList'" value="生徒管理画面" />
    <input type="button" onclick="location.href='teacherList'" value="講師管理画面" />
    <input type="button" onclick="location.href='lectureList'" value="講義管理画面" />
    <input type="button" onclick="location.href='lessonList'" value="科目管理画面" />
    <input type="button" onclick="location.href='classList'" value="クラス管理画面" />
    <input type="button" onclick="location.href='attendList'" value="出席生徒一覧表示画面" />
</body>
</html>