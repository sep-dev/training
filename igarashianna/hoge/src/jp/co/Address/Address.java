package jp.co.Address;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Addressbook")
public class Address extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        //リクエストパラメーラを取得
				request.setCharacterEncoding("Windows-31J");
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				int maxID = 0;

				//リクエストパラメータをチェック
				String msg ="登録成功！！！！！！！";
				String errorMsg ="登録失敗！！！！！！！<br>空欄を埋めてください！";


				int flag =0;

				if(name== null || name.length() == 0) {
					flag = 1;

				}
				if (address== null || address.length() == 0) {
					flag = 1;

				}
				if (tel== null || tel.length() == 0) {
					flag = 1;
				}
					//表示するメッセージを設定
					if(flag == 1){
						msg = errorMsg;
					}

				Connection conn = null;

				try{
					//JDBCドライバを読み込み
					Class.forName("com.mysql.jdbc.Driver");

					//データベースへ接続
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Addresslist","root","03294163aA");


					//ID数発行用のSELECT文を準備
					String sqlselect = "select count(*) cnt from tbAddress";
					PreparedStatement pStmt = conn.prepareStatement(sqlselect);

					//SELECTを実行し、結果表(ResultSet)を取得
					ResultSet rs = pStmt.executeQuery();
					if(rs.next()){
						maxID = rs.getInt("cnt");
					}

					System.out.println("maxID="+maxID);


				} catch (ClassNotFoundException e) {
					System.out.println("データベースに接続できません" + e);
					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("ドライバーのロードに失敗しました" + e);
					e.printStackTrace();
				}finally{
					//データベース切断
					if(conn != null){
						try{
							conn.close();
						} catch(SQLException e) {
							e.printStackTrace();

						}
					}

				}

				//HTMLを入力
				response.setContentType("text/html; charset=Windows-31J");
				PrintWriter out =response.getWriter();
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"Windows-31J\">");
				out.println("<title>登録画面</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<p>" + msg +"</p>");
				if(flag == 0)
					out.println("<input type=button value=一覧表示 onclick=location.href='Addresslist.jsp'>");
				if(flag == 1)
					out.println("<input type=button value=新規登録 onclick=location.href='Addressbook.jsp'>");
				out.println("</body>");
				out.println("</html>");



			}

	}
