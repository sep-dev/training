import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Insert
 */
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
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


		request.setCharacterEncoding("Windows-31J");
		response.setContentType("text/html; charset=Windows-31J");

		//PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		boolean flag1,flag2;

		CheckStr check = new CheckStr();

		//入力値のチェック
		flag1 = check.check(name);
		if(flag1==true){
			flag1 = check.check(address);
			if(flag1==true){
				flag1 = check.check(tel);
			}
		}

		if(flag1 == true){

			java.sql.Connection con = null;

			SQL sql = new SQL();
			con = sql.connect();
			flag2 = sql.insert(con, name, address, tel);

			if(flag2 == true){
				RequestDispatcher disp = request.getRequestDispatcher("success1.jsp");
				disp.forward(request, response);
			}else{
				RequestDispatcher disp = request.getRequestDispatcher("errordb.jsp");
				disp.forward(request, response);
			}
		}else{
			RequestDispatcher disp = request.getRequestDispatcher("error1.jsp");
			disp.forward(request, response);
		}

	}

}