import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

		String id = request.getParameter("id");

		Connection con = null;
		SQL sql = new SQL();

		con = sql.connect();

		if(con != null){
			ResultSet rs = sql.select(con, id);
			try{
				rs.next();
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");

				out.println(" 氏名: "+ name);
				out.println(" 住所: "+ address);
				out.println(" 電話番号: "+ tel);
				out.println("<br><br>");

				rs.close();
				con.close();
			}catch(Exception e){
				RequestDispatcher disp = request.getRequestDispatcher("errordb.jsp");
				disp.forward(request, response);
			}


		}else{
			RequestDispatcher disp = request.getRequestDispatcher("errordb.jsp");
			disp.forward(request, response);
		}

		out.println("<form action=\"ShowAll\" method=\"get\">");
		out.println("<input type=\"submit\" value=\"一覧表示\">");
		out.println("</form>");

		out.println("<form action=\"Delete\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"id\" value="+ id +">");
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
		String id = request.getParameter("id");
		boolean flag;
		java.sql.Connection con = null;
		SQL sql = new SQL();
		con = sql.connect();

		if(con != null){
			flag = sql.delete(con, id);
			if(flag == true){
				RequestDispatcher disp = request.getRequestDispatcher("delete.jsp");
				disp.forward(request, response);
			}else{
				RequestDispatcher disp = request.getRequestDispatcher("errordb.jsp");
				disp.forward(request, response);
			}
		}else{
			RequestDispatcher disp = request.getRequestDispatcher("errordb.jsp");
			disp.forward(request, response);
		}
	}

}
