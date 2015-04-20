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
 * Servlet implementation class ShowAll
 */
public class ShowAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAll() {
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

		PrintWriter out=response.getWriter();


		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"${pageContext.request.contextPath}/../style.css\" />");
		out.println("<title>一覧表示</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>会員一覧</h1>");
		out.println("↓一つだけ選択");

		Connection con = null;
		SQL sql = new SQL();

		con = sql.connect();

		if(con != null){
			ResultSet rs = sql.select(con, null);
			try{
				out.println("<table border=\"1\">");
				out.println("<tr><th></th><td>氏名</td><td>住所</td><td>電話番号</td></tr>");

				int num=1;
				out.println("<form action=\"Select\" method=\"get\">");
				while(rs.next()){
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					String id = rs.getString("id");

					out.println("<tr>");
					out.println("<th>");
					if(num==1){
						out.println("<input type=\"radio\" name=\"list\" value="+ id +" checked>");
					}else{
						out.println("<input type=\"radio\" name=\"list\" value="+ id +">");
					}
					out.println("</th>");

					out.println("<td>");
					out.println( name);
					out.println("</td>");
					out.println("<td>");
					out.println(address);
					out.println("</td>");
					out.println("<td>");
					out.println(tel);
					out.println("</td>");
					out.println("<tr>");
					num++;
				}
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

		out.println("</table>");
		out.println("<br>");
		out.println("<input type=\"submit\" value=\"更新/削除\">");

		out.println("</form>");

		out.println("<br><br><br>");
		out.println("<a href=\"addressbook.jsp\">");
		out.println("<input type=\"button\" value=\"新規登録\">");

		out.println("</a>");

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
