<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body>
    <div align="center">
    <h1>${message}</h1>
    <form class="well" method="POST" action="<c:url value="/j_spring_security_check"/>">
        <table>
            <tr>
                <td align="right" class="col-sm-3">ユーザーID:</td>
                <td class="col-sm-6"><input type="text" name="j_username"/></td>
            </tr>
            <tr>
                <td align="right" class="col-sm-3">パスワード:</td>
                <td class="col-sm-6"><input type="password" name="j_password"/></td>
            </tr>
            <tr>
                <td align="right" class="col-sm-3"/>
                <td colspan="2" class="col-sm-6">
                    <input type="submit" value="ログイン" class="btn btn-primary"/>
                </td>
            </tr>
        </table>
    </form>
    </div>
</body>
</html>