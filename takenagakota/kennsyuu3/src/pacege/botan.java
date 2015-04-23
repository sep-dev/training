package pacege;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

<<<<<<< HEAD
import javax.servlet.RequestDispatcher;
=======
>>>>>>> origin/master
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class botan
 */
public class botan extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public botan() {
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
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		String dennwa=request.getParameter("dennwa");
<<<<<<< HEAD

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
=======
		
		if(name !="" && pass!="" && dennwa!="");{
		out.println(name);
		out.println(pass);
		out.println(dennwa);
		}
	
		
				

		
>>>>>>> origin/master
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/zyuusyoroku","root","BACK-ON7");
			conn.setAutoCommit(false);
			Statement smt=conn.createStatement();
			int count=smt.executeUpdate("INSERT INTO tbaddress(name,zyuusyo,bangou) VALUES('"+name+"','"+pass+"','"+dennwa+"')");
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
		}finally{
			try {
<<<<<<< HEAD

=======
>>>>>>> origin/master
				conn.close();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}
<<<<<<< HEAD
		RequestDispatcher disp =getServletContext().getRequestDispatcher("/seikou.jsp");
		disp.forward(request, response);
=======
>>>>>>> origin/master
	}
}




