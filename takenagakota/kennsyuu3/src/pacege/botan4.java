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
 * Servlet implementation class botan4
 */
public class botan4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public botan4() {
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
		response.setContentType("text/plain; charset=Windows-31J");

		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		String name=request.getParameter("simei");
		String pass=request.getParameter("ie");
		String dennwa=request.getParameter("tell");
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/zyuusyoroku","root","BACK-ON7");
			conn.setAutoCommit(false);
			Statement smt= conn.createStatement();
			int count=smt.executeUpdate("delete from tbaddress where id="+(id));
			conn.commit();
			out.println("通りました");
		}catch(SQLException e){
			e.printStackTrace();
			try {
				conn.rollback();
				out.println("rollback");
			}catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
				e1.printStackTrace();

				out.println("error１");
			}


		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			out.println("error２");
		}finally{
			try {

				conn.close();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
				out.println("error3");
			}
		}
		RequestDispatcher disp =getServletContext().getRequestDispatcher("/sakuzyo.jsp");
		disp.forward(request, response);
		}

	}


