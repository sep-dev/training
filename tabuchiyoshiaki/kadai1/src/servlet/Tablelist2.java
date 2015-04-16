package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class kadai1
 */
public class Tablelist2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tablelist2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String url = "jdbc:mysql://localhost/kadai1";
        String user = "root";
        String pass = "onrain14";


        try {

            // ドライバクラスをロード
            Class.forName("com.mysql.jdbc.Driver");

            // データベースへ接続
            con = DriverManager.getConnection(url,user,pass);
            Statement stmt = con.createStatement();

            // name,bloodType,ageのデータを検索するSQL文を作成
            String sql = "SELECT * FROM tbaddress";
            ResultSet rs = stmt.executeQuery(sql);

            response.setContentType("text/html; charset=Windows-31J");

            PrintWriter out = response.getWriter();

            // 検索された行数分ループ
            while(rs.next()) {

                // idデータを取得
                String id = rs.getString("id");
                // nameデータを取得
                String name = rs.getString("name");
                // addressデータを取得
                String address = rs.getString("address");
                // telデータを取得
                String tel = rs.getString("tel");

                // データの表示
               out.println("id;"+" "+id);
                out.println("name;"+" "+name );
                out.println("address;"+" "+address );
                out.println("tel;"+" "+tel );
            }
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
      
        } catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
