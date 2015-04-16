package kadai;

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
 * Servlet implementation class change
 */
public class change extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public change() {
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

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//touroku.jspの値の取得
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tell = request.getParameter("tell");
		String id = request.getParameter("id2");

		Connection conn = null;
	      String url = "jdbc:mysql://localhost/sample";
	      String user = "root";
	      String pass = "sazi6675";

	      System.out.println(name);
	      System.out.println(address);
	      System.out.println(tell);
	      System.out.println(id);

	    //ページ遷移の判定
	  	//フォームの中がからじゃない
	        if(name != "" && address != "" && tell != ""){
	      //フォームの中がnullじゃない
	  	      if(name != null && address != null && tell != null){
	  	//tellが10文字ある
	  	    	  if(tell.length() == 10){
	  	//成功
	  	    		try {
		    		      //データベースに接続
		    			  conn = DriverManager.getConnection(url, user, pass);

		    		      //SQL ステートメント・オブジェクトの作成
		    			 Statement stmt = conn.createStatement();
		    			 //SQL ステートメントの発行
		    			 String  query2 = "UPDATE sample.tbaddress set name = "+name+", address = "+address+", tel = "+tell+" where id = "+id+"";
		    			 int rs2 = stmt.executeUpdate(query2) ;

		    			 //データベースのクローズ
		    				stmt.close();
		    				conn.close();

		    				RequestDispatcher dispatcher = request.getRequestDispatcher("k_seikou.jsp");
		    				dispatcher.forward(request, response);

		    		    } catch (SQLException e) {
		    		    	//表示
		    		    	 response.setContentType("text/html; charset=Shift_JIS");
		    		    	    PrintWriter out = response.getWriter();

		    		    	    out.println("例外発生：" + e );
		    		    	    System.out.println("失敗" + e);
		    		    }
	  	    	//以下失敗
				  }else{
						RequestDispatcher dispatcher = request.getRequestDispatcher("k_sippai_tell.jsp");
						dispatcher.forward(request, response);
				  }
		      }else{
					RequestDispatcher dispatcher = request.getRequestDispatcher("k_sippai.jsp");
					dispatcher.forward(request, response);
		      }
	      }else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("k_sippai.jsp");
				dispatcher.forward(request, response);
	      }
		}
}
