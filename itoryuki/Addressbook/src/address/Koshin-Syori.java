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
 * Servlet implementation class Kousinseihi
 */
public class Koshin-Syori extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Koshin-Syori() {
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

		String name1;
		String  address1;
		String tel1;
		String selectID;

		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		name1 = request.getParameter("name");
		address1 = request.getParameter("address");
		tel1 = request.getParameter("tel");

		//リクエストパラメータをチェック
		if(name1 != "" && address1 != "" && tel1 != ""){

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db1","root","ROKOroko0321");

			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			String sql = "update addresstable set
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
			request.getRequestDispatcher("/WEB-INF/jsp/Koshin-Sippai.jsp");
			dispatcher.forward(request, response);
			}
		}else{
			//登録失敗画面へ
			RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/jsp/Koshin-Sippai.jsp");
			dispatcher.forward(request, response);
		}
	}
}
