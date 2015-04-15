package address;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Addressbook2
 */


public class Addressbook2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addressbook2() {
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

		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		//リクエストパラメータをチェック
		String errorMsg = null;
		String intA = null;
		if(name != null && address != null && tel != null){
			errorMsg ="登録成功！！！！！！！<br>";
			intA="一覧表示";
				System.out.println("");
		}else{
			//if(name == null || address == null || tel == null)
			errorMsg ="登録失敗！！！！！！！<br>";
			intA="新規登録";


		}

		//HTMLを出力
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<mtea charset=\"UTF-8\">" );
		out.println("</head>");
		out.println("<body>");
		out.println("<p>"  + errorMsg + "</p>");
		out.println("<input type="submit"");
		out.println("</body>");
		out.println("</html>");
	}

}
