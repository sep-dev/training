package addressbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Addressbook_failure
 */
public class Addressbook_failure extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addressbook_failure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

	    PrintWriter out = response.getWriter();
	    //StringBuffer sb = new StringBuffer();

	    out.println("<html>");
	    out.println("  <body>");
	    out.println("  <h1><center>登録失敗！！！！！！</center></h1>");
	    out.println("  <h1><center>空欄を埋めてください！</center></h1>");
	    out.println("  <a href = \"Addressbook.jsp\">");
	    out.println("  <center><button>新規登録</button></center> ");
	    out.println("  </a>");
	    out.println("  </body>");
	    out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
