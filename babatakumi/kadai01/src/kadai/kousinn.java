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
 * Servlet implementation class kousinn
 */
public class kousinn extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public kousinn() {
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

		  response.setContentType("text/html; charset=UTF-8");
		  request.setCharacterEncoding("UTF-8");

	      String id = request.getParameter("list");

	      Connection conn = null;
	      String url = "jdbc:mysql://localhost/sample";
	      String user = "root";
	      String pass = "sazi6675";

		try {
//データベースに接続
			conn = DriverManager.getConnection(url, user, pass);

//表示
			PrintWriter out = response.getWriter();

//SQL ステートメント・オブジェクトの作成
			Statement stmt = conn.createStatement();

				 //SQL ステートメントの発行
			String  query = "SELECT * from sample.tbaddress where id = "+id;
			ResultSet rs = stmt.executeQuery(query) ;
			rs.next();

				out.println("<html>");
				out.println("<head>");
				out.println("<title>" +  "会員情報の更新/削除</title>");
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"${pageContext.request.contextPath}/../servlet_css.css\">");

				out.print("<SCRIPT language=\"JavaScript\">");
				out.println("function numOnly() {");
				out.println("var str =\"document.iform.AgeText.value;\"");
				out.println("if(str.match( /[^0-9]+/ ) return false;");
				out.println("return true;");
				out.println("}");
				out.println("</SCRIPT>");

				out.println("</head>");
				out.println("<body>");

				out.println("<h1>"+ "更新</h1><br>");

			int id2 = rs.getInt("id");
			String name = rs.getString("name");
			String address = rs.getString("address");
		    String tell = rs.getString("tel");

			    out.println("<p>"+"名前：" + name + "住所：" + address + "電話：" + tell+"</p>" );

			    out.println("<form action=\""+"/kadai01/change\" method=\"post\">");
			    out.println("<p>");
			    out.println("<label for=name> 氏名:</label>");
			    out.println("<input type=\"text\" name=\"name\">");
			    out.println("</p>");
			    out.println("<p>");
			    out.println("<label for=name> 住所:</label>");
			    out.println("<input type=\"text\" name=\"address\">");
			    out.println("</p>");
			    out.println("<p>");
			    out.println("<label for=name> 電話:</label>");
			    out.println("<input type=\"text\" name=\"tell\" maxlength=\"10\" onkeyDown=\"return numOnly()\">");
			    out.println("</p>");

	      		out.println("<input type=\"hidden\" name=\"id\" value= " +id2+">");
				out.println("<input type=\"submit\" value=\"更新\">");
				out.println("<input type=\"reset\" value=\"リセット\">");
				out.println("</form>");

				out.println("<form action=\""+ "/kadai01/itirann\" method=\"post\">");
				out.println("<input type=\"submit\" value=\"一覧表示\">");
				out.println("</form>");

				out.println("<form action=\""+ "/kadai01/sakujo \" method=\"post\">");
				out.println("<input type=\"hidden\" name=\"id\" value= " +id2+">");
				out.println("<input type=\"submit\" value=\"削除\">");
				out.println("</form>");

				out.println("</body>");
				out.println("</html>");

			  //データベースのクローズ
			 rs.close();
			 stmt.close();
			 conn.close();

		    } catch (SQLException e) {
//表示
		    	 response.setContentType("text/html; charset=UTF-8");
		    	    PrintWriter out = response.getWriter();

		    	    out.println("例外発生：" + e );
		    	    System.out.println("例外発生：" + e);
		    }
		}
}
