package addressbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Addressbook_success
 */
public class Addressbook_success extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addressbook_success() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
	    //StringBuffer sb = new StringBuffer();
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"${pageContext.request.contextPath}/../css/style.css\">");
		out.println("<br />");
	    out.println("<html>");
	    out.println("  <body>");
	    out.println("  <h1>登録成功！！！！！！</h1>");
	    out.println("  <a href = \"itiran.jsp\">");
	    out.println("  <button>一覧表示</button> ");
	    out.println("  </a>");
	    out.println("  </body>");
	    out.println("</html>");


	    /*
	    String type = request.getParameter("type");
	    if ("jsp".equals(type)) {
	    	RequestDispatcher disp = request.getRequestDispatcher("http://localhost:8080/hoge/Addressbook.jsp");
	    	disp.forward(request, response);
	    }
	    */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
