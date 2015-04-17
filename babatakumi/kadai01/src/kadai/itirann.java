package kadai;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class itirann
 */
public class itirann extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public itirann() {
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

		Connection conn = null;
	      String url = "jdbc:mysql://localhost/sample";
	      String user = "root";
	      String pass = "sazi6675";

		try {

		      //データベースに接続
			  conn = DriverManager.getConnection(url, user, pass);

			  //表示
		      response.setContentType("text/html; charset=Shift_JIS");
		      PrintWriter out = response.getWriter();

		      //SQL ステートメント・オブジェクトの作成
			 Statement stmt = conn.createStatement();

			 //SQL ステートメントの発行
			 String  query = "SELECT * from tbaddress";
			 ResultSet rs = stmt.executeQuery(query) ;


			 out.println("<html>");
			 out.println("<head>");
			 out.println("<title>" +  "一覧画面</title>");
			 out.println("</head>");
			 out.println("<body>");

			 out.println("<h>"+ "会員一覧</h><br>");

			 out.println("<form action=\""+ "/kadai01/kousinn\" method=\"post\">");
			 int num = 1;
			 while(rs.next()){
				    int id = rs.getInt("id");
				    String name = rs.getString("name");
				    String address = rs.getString("address");
				    String tell = rs.getString("tel");

				    out.println("<INPUT TYPE=\"radio\" name=\"list\" value="+id+">");
				    out.println("ID：" + id + "名前：" + name + "住所：" + address + "電話：" + tell );
				    out.println("<br>");
				    num++;
				  }
			//out.println("</form>");


			 out.println("<input type=\"submit\" value=\"更新or削除\">");
			 out.println("</form>");

			 out.println("<form action=\""+ "/kadai01/touroku.jsp\" method=\"post\">");
			 out.println("<input type=\"submit\" value=\"新規作成\">");
			 out.println("</form>");

			 out.println("</body>");
			 out.println("</html>");


			 //データベースのクローズ
				rs.close();
				stmt.close();
				conn.close();

		    } catch (SQLException e) {
		    	//表示
		    	 response.setContentType("text/html; charset=Shift_JIS");
		    	    PrintWriter out = response.getWriter();

		    	    out.println("例外発生：" + e );
		    	    System.out.println("例外発生：" + e);
		    }
	}

}
