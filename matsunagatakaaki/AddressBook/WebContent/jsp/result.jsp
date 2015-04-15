<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String TITLE = "title";
   String H1_TEXT = "h1";
   String URL = "url";
   String VALUE = "btnValue";
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/common.css" />
    <link rel="stylesheet" type="text/css" href="./css/result.css" />
  <title><% out.print((String)request.getAttribute(TITLE)); %></title>
  </head>
  <body>
    <section>
      <h1 class="title"><% out.print((String)request.getAttribute(H1_TEXT)); %></h1>
      <form action="<%out.print((String)request.getAttribute(URL)); %>" >
        <p><input type="submit" value="<%out.print((String)request.getAttribute(VALUE)); %>" /></p>
      </form>
    </section>
  </body>
  <%
    request.removeAttribute(TITLE);
    request.removeAttribute(H1_TEXT);
    request.removeAttribute(URL);
    request.removeAttribute(VALUE);
  %>
</html>