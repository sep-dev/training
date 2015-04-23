package pacege;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class botan2
 */
public class botan2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public botan2() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("Windows-31J");
		response.setContentType("text/plain; charset=Windows-31J");

		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		String dennwa=request.getParameter("dennwa");

		if(name.trim() =="" || pass.trim() =="" || dennwa.trim() == ""){
			out.println(name);
			out.println(pass);
			out.println(dennwa);
			RequestDispatcher disp =getServletContext().getRequestDispatcher("/sippai.jsp");
			disp.forward(request, response);
			return;
		}

		//if(name !=" " && pass!=" " && dennwa!=" "){
		//	out.println(name);
		//	out.println(pass);
		//	out.println(dennwa);
		//	//return;
		//}
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/zyuusyoroku","root","BACK-ON7");
			conn.setAutoCommit(false);
			Statement smt=conn.createStatement();
			int count=smt.executeUpdate("UPDATE tbaddress(id,name,zyuusyo,bangou) VALUES('"+id+"','"+name+"','"+pass+"','"+dennwa+"')");
			conn.commit();
			System.out.println("通りました");
		}catch(SQLException e){
			e.printStackTrace();
			try {
				conn.rollback();
			}catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
	} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

}}
