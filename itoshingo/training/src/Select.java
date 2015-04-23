import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Select() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("Windows-31J");
		response.setContentType("text/html; charset=Windows-31J");

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"${pageContext.request.contextPath}/../style.css\" />");
		out.println("<title>更新/削除</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>会員情報の更新</h1>");

		String id = request.getParameter("list");
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


		out.println("<form action=\"Update\"method=\"post\">");
		out.println("<input type=\"hidden\" name=\"id\" value="+ id +">");
		out.println("<table aling = \"center\">");
		out.println("<tr><th>");
		out.println("氏名 ");
		out.println("</th><td>");
		out.println("<input type=\"text\" name=\"name\" style=\"width:200px;\">");
		out.println("</td></tr><tr><th>");
		out.println("住所 ");
		out.println("</th><td>");
		out.println("<input type=\"text\" name=\"address\" style=\"width:200px;\">");
		out.println("</td></tr><tr><th>");
		out.println("電話番号 ");
		out.println("</th><td>");
		out.println("<input type=\"text\" name=\"tel\" maxlength=\"11\" style=\"width:200px;\">");
		out.println("</td></tr></table>");
		out.println("<br>");
		out.println("<input type=\"submit\"value=\"更新\">");
		out.println("<input type=\"reset\" value=\"リセット\">");
		out.println("</form>");
		out.println("<br><br>");
		out.println("<form action=\"ShowAll\" method=\"get\">");
		out.println("<input type=\"submit\" value=\"一覧表示\">");
		out.println("</form>");
		out.println("<br><br>");
		out.println("<form action=\"Delete\" method=\"get\">");
		out.println("<input type=\"hidden\" name=\"id\" value="+ id +">");
		out.println("<input type=\"submit\" value=\"削除\">");
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
