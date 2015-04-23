

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
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
		out.println("<link rel=\"stylesheet\" href=\"${pageContext.request.contextPath}/../style.css\" />");
		out.println("<title>削除確認</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>本当に削除してもいいですか？</h3>");

		Connection con = null;
		PreparedStatement  ps;

		String num = request.getParameter("id");

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

			//選択されたデータの表示
			out.println(" 氏名: "+ name);
			out.println(" 住所: "+ address);
			out.println(" 電話番号: "+ tel);
			out.println("<br><br>");


			ps.close();
			rs.close();
			con.close();


		}catch(Exception e){
/*			RequestDispatcher disp = request.getRequestDispatcher("errordb.jsp");
			disp.forward(request, response);*/

			out.print("Error");
		}



		out.println("<form action=\"ShowAll\" method=\"get\">");
		out.println("<input type=\"submit\" value=\"一覧表示\">");
		out.println("</form>");

		out.println("<form action=\"Delete\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"id\" value="+num+">");
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

		request.setCharacterEncoding("Windows-31J");
		response.setContentType("text/html; charset=Windows-31J");
		PrintWriter out = response.getWriter();

		java.sql.Connection con = null;
		PreparedStatement ps = null;
		String num = request.getParameter("id");

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/address","root","zxcASDqwe");

			String sql = "delete from tbaddress where id=?";
			ps = con.prepareStatement(sql);

			ps.setString(1,num);

			int z = ps.executeUpdate();

			ps.close();
			con.close();

			RequestDispatcher disp = request.getRequestDispatcher("delete.jsp");
			disp.forward(request, response);

		}catch(Exception e){
			RequestDispatcher disp = request.getRequestDispatcher("errordb.jsp");
			disp.forward(request, response);
		}
	}

}
