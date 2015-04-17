

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Select
 */
public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Select() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("Windows-31J");
		response.setContentType("text/html; charset=Windows-31J");

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>更新/削除</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>会員情報の更新</h1>");

		Connection con = null;
		PreparedStatement  ps;

		String num = request.getParameter("list");

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/address","root","zxcASDqwe");

			String sql = "select * from tbaddress where id = "+ num;

			ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			rs.next();


			String name = rs.getString("name");
			String address = rs.getString("address");
			String tel = rs.getString("tel");

			out.println(" 氏名: "+ name);
			out.println(" 住所: "+ address);
			out.println(" 電話番号: "+ tel);
			out.println("<br><br>");


			ps.close();
			rs.close();
			con.close();


		}catch(Exception e){
			RequestDispatcher disp = request.getRequestDispatcher("errordb.jsp");
			disp.forward(request, response);
		}

		out.println("<form action=\"Update\"method=\"post\">");
		out.println("<input type=\"hidden\" name=\"id\" value="+ num +">");
		out.println("氏名 ");
		out.println("<input type=\"text\" name=\"name\">");
		out.println("<br>");
		out.println("住所 ");
		out.println("<input type=\"text\" name=\"address\">");
		out.println("<br>");
		out.println("電話番号 ");
		out.println("<input type=\"text\" name=\"tel\" maxlength=\"10\">");
		out.println("<br>");

		out.println("<input type=\"submit\"value=\"更新\">");
		out.println("<input type=\"reset\" value=\"リセット\">");
		out.println("</form>");
		out.println("<form action=\"ShowAll\" method=\"get\">");
		out.println("<input type=\"submit\" value=\"一覧表示\">");
		out.println("</form>");

		out.println("<form action=\"Delete\" method=\"get\">");
		out.println("<input type=\"hidden\" name=\"id\" value="+ num +">");
		out.println("<input type=\"submit\" value=\"削除\">");
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
