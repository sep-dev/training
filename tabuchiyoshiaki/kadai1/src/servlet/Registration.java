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

public class Registration extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
	  response.setContentType("text/html; charset=UTF-8");
	  request.setCharacterEncoding("UTF-8");
	  String disp = "/Success";
	  RequestDispatcher dispatch = request.getRequestDispatcher(disp);

	  dispatch.forward(request, response);

    
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Registration</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("Registration");
    Connection conn = null;
    int id = 9;
    String url = "jdbc:mysql://localhost/kadai1";
    String user = "root";
    String password = "onrain14";

    try {

    	String name = request.getParameter("name") ;
    	String address = request.getParameter("address") ;
    	String tel =request.getParameter("tel");
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(url, user, password);

      Statement stmt = conn.createStatement();

      String sql = "insert into tbaddress (name,address,tel) values(\"" + name + "\", \"" + address + "\",\"" + tel +"\");";
      int num = stmt.executeUpdate(sql);




      stmt.close();
    }catch (ClassNotFoundException e){
      out.println("ClassNotFoundException:" + e.getMessage());
    }catch (SQLException e){
      out.println("SQLException:" + e.getMessage());
    }catch (Exception e){
      out.println("Exception:" + e.getMessage());
    }finally{
      try{
        if (conn != null){
          conn.close();
        }
      }catch (SQLException e){
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