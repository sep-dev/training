package servlet;

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

public class Update extends HttpServlet {
	public String sql;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String disp;

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Update</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://localhost:8080/kadai1/css/design.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("Update");
		Connection conn = null;
		String url = "jdbc:mysql://localhost/kadai1";
		String user = "root";
		String password = "onrain14";

		try {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			String id = request.getParameter("id");

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);

			Statement stmt = conn.createStatement();
			if (name == "" && address == "" && tel == "") {
				disp = "/Update_n";
				RequestDispatcher dispatch = request.getRequestDispatcher(disp);

				dispatch.forward(request, response);

			} else {
				sql = "update tbaddress set name = ('" + name
						+ "'),address= ('" + address + "'),tel = ('" + tel
						+ "') where id =" + id + " ;";
				stmt.executeUpdate(sql);
				disp = "Update_success";
				RequestDispatcher dispatch = request.getRequestDispatcher(disp);

				dispatch.forward(request, response);
			}

			// +"’, address =’"+address+"’, tel =' "+tel+"' where id = 31");
			// String sql = "update tbaddress set name='\"" + name +
			// "\"', address='\"" + address + "\"',tel='\"" + tel
			// +"\"' where id = " + id +";";
			// int num = stmt.executeUpdate(sql);

			// sql = "select * from tbaddress";
			// ResultSet rs = stmt.executeQuery(sql);
			// rs.next();

			// rs.close();
			System.out.println(sql);
			stmt.close();
		} catch (ClassNotFoundException e) {
			out.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException e) {
			out.println("SQLException:" + e.getMessage());
		} catch (Exception e) {
			out.println("Exception:" + e.getMessage());
		} finally {
			System.out.println(sql);
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				out.println("SQLException:" + e.getMessage());
			}
		}

		out.println("</body>");
		out.println("</html>");
	}

	private String readLine() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}