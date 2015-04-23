package address;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	/**
	* Servlet implementation class Tourokusyori
	*/

public class TorokuSyori extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* タグを無害化します。
	* @param str
	* @return
	*/
	private static String convertSanitize( String str ) {
		if(str==null) {
			return str;
		}
		str = str.replaceAll("&" , "&amp;" );
		str = str.replaceAll("<" , "&lt;"  );
		str = str.replaceAll(">" , "&gt;"  );
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'" , "&#39;" );
		return str;
	 }

	/**
	* 無害化されたタグを元に戻します
	* @param str
	* @return
	*/
	private static String convertUnsanitize( String str ) {
		if(str==null) {
			return str;
			}
		str = str.replaceAll("&#39;" , "'" );
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&gt;"  , ">" );
		str = str.replaceAll("&lt;"  , "<" );
		str = str.replaceAll("&amp;" , "&" );
		return str;
	 }
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TorokuSyori() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html; charset=utf-8");
		String name1;
		String  address1;
		String tel1;

		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		name1 = request.getParameter("name");
		address1 = request.getParameter("address");
		tel1 = request.getParameter("tel");

			String name2 = name1;
			String address2 = address1;
			String tel2 = tel1;
			String sql=null;
			//リクエストパラメータをチェック
			if(name2 != "" && address2 != "" && tel2 != ""){

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db1","root","ROKOroko0321");

					conn.setAutoCommit(false);
					Statement stmt = conn.createStatement();
					sql = "insert into addresstable(name, address, tel) values(\""+name2+"\", \""+address2+"\", \""+tel2+"\");";
					int rs = stmt.executeUpdate(sql);
					stmt.close();
					conn.commit();
					System.out.print(sql);

				//登録成功画面へ
				RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/Toroku-Seiko.jsp");
				dispatcher.forward(request, response);

				}catch(Exception e){
					e.printStackTrace();

					//登録失敗画面へ
					RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/Toroku-Shippai.jsp");
					dispatcher.forward(request, response);
				}
			}else{
				System.out.print(sql);
					//登録失敗画面へ
					RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/jsp/Toroku-Shippai.jsp");
					dispatcher.forward(request, response);
			}
		}

	private void value(String string, String string2, String string3) {
		// TODO 自動生成されたメソッド・スタブ
	}
}
