package addressbook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Insert
 */
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		Connection con = null;

		String url = "jdbc:mysql://localhost/kadai1";
		String user = "root";
		String password = "pass";

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		if (name == "" || address == "" || tel == "") {
			String disp = "/Addressbook_failure";
			RequestDispatcher dispatch = request.getRequestDispatcher(disp);
			dispatch.forward(request, response);

		} else {
			try {

				// ドライバクラスをロード
				Class.forName("com.mysql.jdbc.Driver");

				// データベースへ接続
				con = DriverManager.getConnection(url, user, password);
				// ステートメントの作成
				Statement smt = con.createStatement();
				// id,name,address,telのデータを追加するSQL文を作成
				String sql = "insert into tbAddress(name,address,tel) values ('"
						+ name + "','" + address + "','" + tel + "');";
				// クエリーを実行して結果セットを取得
				smt.executeUpdate(sql);

				// 登録成功画面のアドレス登録
				String disp = "/Addressbook_success";
				RequestDispatcher dispatch = request.getRequestDispatcher(disp);
				dispatch.forward(request, response);
				// 検索された行数分ループ

				smt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {

					// close処理
					if (con != null) {
						con.close();
					}

					// close処理
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
