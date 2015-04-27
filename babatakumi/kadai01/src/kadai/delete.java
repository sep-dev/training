package kadai;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class delete
 */
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete() {
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
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//touroku.jspの値の取得
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tell = request.getParameter("tell");
		String id = request.getParameter("id");

		Connection conn = null;
	      String url = "jdbc:mysql://localhost/sample";
	      String user = "root";
	      String pass = "sazi6675";

		try {
		      //データベースに接続
			  conn = DriverManager.getConnection(url, user, pass);

		     //SQL ステートメント・オブジェクトの作成
			 Statement stmt = conn.createStatement();
			 //SQL ステートメントの発行
			 String  query2 = "DELETE FROM sample.tbaddress where id = "+id+"";
			 int rs2 = stmt.executeUpdate(query2) ;

			 //データベースのクローズ
				stmt.close();
				conn.close();

				request.setAttribute("flag", "3");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/seikou.jsp");
				dispatcher.forward(request, response);

		    } catch (SQLException e) {
		    	//表示
		    	    PrintWriter out = response.getWriter();

		    	    out.println("例外発生：" + e );
		    	    System.out.println("失敗" + e);
		    }
	}
}
