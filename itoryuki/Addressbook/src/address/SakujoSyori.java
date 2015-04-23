package address;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SakujoSyori
 */

public class SakujoSyori extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SakujoSyori() {
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

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs =null;

	String sakujoID1;

	sakujoID1 =request.getParameter("ID");
	System.out .println(sakujoID1);

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db1","root","ROKOroko0321");

				stmt = conn.createStatement();

				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				String sql = "delete  from addresstable where id =" +sakujoID1+";";
				int num = stmt.executeUpdate(sql);
				stmt.close();
				conn.commit();

				RequestDispatcher dispatcher =
						request.getRequestDispatcher("/WEB-INF/jsp/Sakujo-Seiko.jsp");
						dispatcher.forward(request, response);

				}catch(Exception e){
					e.printStackTrace();
				} finally {
						 if (conn != null){
							 try {
								conn.close();
							} catch (SQLException e) {
								// TODO 自動生成された catch ブロック
								e.printStackTrace();
							}
							 	System.out.println("aaaaaa");
			}
	}
	}
}