package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

public class Not_update extends HttpServlet {
	public	String sql;
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
	  response.setContentType("text/html; charset=UTF-8");
	  request.setCharacterEncoding("UTF-8");
	  String disp = "/Delete_com";
	  RequestDispatcher dispatch = request.getRequestDispatcher(disp);

	  dispatch.forward(request, response);


    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Not_apdate</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("Not_apdate");
    Connection conn = null;
    String url = "jdbc:mysql://localhost/kadai1";
    String user = "root";
    String password = "onrain14";

    try {
    	String id = request.getParameter("id");

      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(url, user, password);

      Statement stmt = conn.createStatement();
      String sql ="delete from tbaddress  where id ="+id+" ;";
      int num = stmt.executeUpdate(sql);
      sql = "select * from kadai1";
      ResultSet rs = stmt.executeQuery(sql);
      System.out.println(sql);
      
      rs.close();
      stmt.close();
    }catch (ClassNotFoundException e){
      out.println("ClassNotFoundException:" + e.getMessage());
    }catch (SQLException e){
      out.println("SQLException:" + e.getMessage());
    }catch (Exception e){
      out.println("Exception:" + e.getMessage());
    }finally{
    	System.out.println(sql);
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