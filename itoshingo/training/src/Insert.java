

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

		//入力値のチェック
		flag1 = check(name);
		if(flag1==true){
			flag1 = check(address);
			if(flag1==true){
				flag1 = check(tel);
			}
		}

		if(flag1 == true){

			flag2 = insert(name,address,tel);

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

	protected boolean check(String x){
		if(!x.matches(".*\".*")&&!x.matches(".*;.*")&&!x.matches(".*<.*")&&!x.matches(".*>.*")&&!x.matches(".*\'.*")&&!x.equals("")){
			return true;
		}
		else{
			return false;
		}
	}

	protected boolean insert(String name,String address, String tel){

		java.sql.Connection con = null;
		PreparedStatement ps = null;

        try {

            // ドライバクラスをロード
           Class.forName("com.mysql.jdbc.Driver");

            // データベースへ接続
           con = DriverManager.getConnection("jdbc:mysql://localhost/address","root","zxcASDqwe");

           String sql = "select count(*) cnt from tbaddress";

           // ステートメントオブジェクトを生成
          ps = con.prepareStatement(sql);

           // クエリーを実行して結果セットを取得
          ResultSet rs = ps.executeQuery();
          rs.next();
          int id = rs.getInt("cnt");

          sql = "insert into tbaddress(name,address,tel) value(?,?,?)";

          ps = con.prepareStatement(sql);

          ps.setString(1, name);
          ps.setString(2,address);
          ps.setString(3,tel);

          int num = ps.executeUpdate();

          ps.close();
          rs.close();
          con.close();

          return true;

        }catch(Exception e){
        	return false;
        }
	}
}