
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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



        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");

        String num = request.getParameter("id");


		boolean flag;

		//入力値のチェック
		flag = check(name);
		if(flag==true){
			flag = check(address);
			if(flag==true){
				flag = check(tel);
			}
		}

		if(flag != false){

		java.sql.Connection con = null;
        PreparedStatement ps = null;

        try{
        	Class.forName("com.mysql.jdbc.Driver");
        	con = DriverManager.getConnection("jdbc:mysql://localhost/address","root","zxcASDqwe");



        	String sql = "update tbaddress set name = ?,address = ?,tel = ? where id = ?";

        	ps = con.prepareStatement(sql);

        	ps.setString(1,name);
        	ps.setString(2,address);
        	ps.setString(3,tel);
        	ps.setString(4,num);

        	int z = ps.executeUpdate();

        	ps.close();
        	con.close();

    		RequestDispatcher disp = request.getRequestDispatcher("success2.jsp");
    		disp.forward(request, response);

        }catch(Exception e){
			RequestDispatcher disp = request.getRequestDispatcher("errordb.jsp");
			disp.forward(request, response);
        }
		}else{
			RequestDispatcher disp = request.getRequestDispatcher("error2.jsp");
			disp.forward(request, response);
		}

	}

	public boolean check(String x){
		if(!x.matches(".*\".*")&&!x.matches(".*;.*")&&!x.matches(".*<.*")&&!x.matches(".*>.*")&&!x.matches(".*\'.*")&&!x.equals("")){
			return true;
		}
		else{
			return false;
		}
	}

}
