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
 * Servlet implementation class check
 */
public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public check() {
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

		//データベース変数
		Connection conn = null;
	      String url = "jdbc:mysql://localhost/sample";
	      String user = "root";
	      String pass = "sazi6675";

	      int flag = 0;
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

	    			 String  query2 = "INSERT INTO sample.tbaddress(name,address,tel)VALUES("+ "\""+name+"\"" +","+ "\""+address+"\"" +","+  "\""+tell+"\"" +");";
	    			 int rs2 = stmt.executeUpdate(query2) ;

	    			 //データベースのクローズ
	    				stmt.close();
	    				conn.close();

	    				flag = 1;
	    				String sflag = String.valueOf(flag);

	    				request.setAttribute("flag", sflag);
	    				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/seikou.jsp");
	    				dispatcher.forward(request, response);

	    		    } catch (SQLException e) {
	    		    	//表示
	    		    	    PrintWriter out = response.getWriter();

	    		    	    out.println("例外発生：" + e );
	    		    	    System.out.println("失敗" + e);
	    		    }
//以下失敗
			  }else{
				  flag = 2;
  					String sflag = String.valueOf(flag);

  					request.setAttribute("flag", sflag);
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/sippai.jsp");
					dispatcher.forward(request, response);
			  }
	      }else{
	    	  flag = 1;
				String sflag = String.valueOf(flag);

				request.setAttribute("flag", sflag);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/sippai.jsp");
				dispatcher.forward(request, response);
	      }
      }else{
    	  flag = 1;
			String sflag = String.valueOf(flag);

			request.setAttribute("flag", sflag);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/sippai.jsp");
			dispatcher.forward(request, response);
      }
	}

}
