package servlet;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Success
 * @param <a>
 */
public class Update_success extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_success() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");





	}


/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // contextの取得
    ServletContext context = getServletConfig().getServletContext();

    // RequestDispatcherの取得
//    RequestDispatcher dis = context.getRequestDispatcher("/NewFile.jsp");
//    // forward設定
//    dis.forward(request, response);

    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<body>");
    out.println("<h1><center>更新成功！！！！！！！</center></h1>");
    out.println("<center>");
    out.println("<a href=\"Tablelist1.jsp\">");
    out.println("<button>一覧表示</button>");
    out.println("</a>");
    out.println("</center>");
    out.println("</body>");
    out.println("</html>");
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}