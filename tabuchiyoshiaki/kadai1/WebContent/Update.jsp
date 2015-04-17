<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<%
try{
request.setCharacterEncoding("UTF-8");
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection(
       "jdbc:mysql://localhost/kadai1",
       "root", "onrain14");


String id = request.getParameter("radioid");
System.out.println(id);

Statement stmt = conn.createStatement();
String sql = "select * from  tbaddress where id = " + id +";";
System.out.println(sql);
ResultSet res = stmt.executeQuery(sql);
res.next();


%>
<h1>会員情報の更新</h1>
<br>



                <%-- レコードのNOフィールドを表示 --%>
          		 <%=   "氏名:"+ res.getString("name")%>
                <%-- レコードのNAMEフィールドを表示 --%>
               <%= "住所:" + res.getString("address")%>
                <%-- レコードのPRICEフィールドを表示 --%>
                <%= "電話番号:" + res.getString("tel")%>
<script type="text/javascript">
<!--

function check(){

	var flag = 0;


	// 設定開始（必須にする項目を設定してください）

	if(document.form1.name.value == ""){ // 「お名前」の入力をチェック

		flag += 1;

	}
	 if(document.form1.address.value == ""){ // 「住所」の入力をチェック

		flag += 1;

	}
	 if(document.form1.tel.value == ""){ // 「電話番号」の入力をチェック

		flag += 1;

	}

	// 設定終了


	if(flag==3){



			// 「OK」時の処理開始 ＋ 確認ダイアログの表示
			if(window.confirm('空欄です！入力してください！！\n一覧表示画面に戻ります')){

				location.href = "Tablelist1.jsp";

			}
			// 「OK」時の処理終了

			// 「キャンセル」時の処理開始
			else{

				window.alert('キャンセルされました'); // 警告ダイアログを表示

			}
			// 「キャンセル」時の処理終了
	}
}

// -->

</script>

<form method="post" action="" name="form1"  onSubmit="return check()">
<p>氏名&nbsp;<input type="text" size="40" value=<%=  res.getString("name")%> name="name" id="sample1" /></p>
   <p>住所&nbsp;<input type="text" size="40" value=<%=  res.getString("address")%> name="address" id="sample1" /></p>
 <p>電話番号&nbsp;<input type="text" size="40" value=<%=  res.getString("tel")%>  name="tel" id="sample1" /></p>
 <br>
  <input type="reset" name="reset" value="リセット">
 </form>
 <p><button onclick="check()">更新</button>

 <form action="Tablelist1.jsp">
 <input type="submit" name="tablelist" value="一覧表示">
 <input type="submit" name="delete" value="削除">
 </form>
 <br>
 <%
}

 catch (ClassNotFoundException e){
      out.println("ClassNotFoundException:" + e.getMessage());
      }
%>
</body>
</html>