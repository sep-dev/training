package address;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Kousinseihi
 */
public class KoshinSyori extends HttpServlet {
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
	    str = str.replaceAll("&" , "＆" );
	    str = str.replaceAll("<" , "＜"  );
	    str = str.replaceAll(">" , "＞"  );
	    str = str.replaceAll("\"", "”");
	    str = str.replaceAll("'" , "’" );
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
    public KoshinSyori() {
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
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;

		//一覧で選択したIDを取得
		String selectID;
		String name1;
		String  address1;
		String tel1;

		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		name1 = request.getParameter("name");
		address1 = request.getParameter("address");
		tel1 = request.getParameter("tel");
		selectID =request.getParameter("ID");
		System.out .println(selectID);

		//リクエストパラメータをチェック
		if(name1 != "" && address1 != "" && tel1 != ""){

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db1","root","ROKOroko0321");

			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql = "update addresstable set name=\""+name1+"\", address=\""+address1+"\", tel=\""+tel1+"\" where id = "+selectID+";";
			int num=stmt.executeUpdate(sql);
			System.out.println(sql);
			stmt.close();
			conn.commit();

		//登録成功画面へ
		RequestDispatcher dispatcher =
		request.getRequestDispatcher("/WEB-INF/jsp/Koshin-Seiko.jsp");
		dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();

			//登録失敗画面へ
			RequestDispatcher dispatcher =
			request.getRequestDispatcher("/WEB-INF/jsp/Koshin-Shippai.jsp");
			dispatcher.forward(request, response);
			}
		}else{
			//登録失敗画面へ
			RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/jsp/Koshin-Shippai.jsp");
			dispatcher.forward(request, response);
		}
	}
}
