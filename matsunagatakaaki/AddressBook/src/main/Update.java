package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection conn = null;
    private PreparedStatement stmt = null;

    private String title,h1_text,url,btnValue; //結果画面での表示値格納
    private boolean error = false; //更新処理途中での、例外発生&失敗フラグ

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding(HtmlHelper.ENCORDING);
		response.setContentType("text/html; charset=" + HtmlHelper.ENCORDING);

		if(!HtmlHelper.searchParameter("update", request)){
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("addressData", this.getDispAddressData(Integer.parseInt(request.getParameter("id"))));
			request.getRequestDispatcher("./jsp/updateValuesInput.jsp").forward(request, response);
		}else{
			if(updateProcess(request)){
				title="更新に成功しました！！";
				h1_text = "更新成功";
			}else{
				title = error ? "更新に失敗しました。" : "空欄です。入力してください！";
				h1_text = "更新失敗";
			}
			request.setAttribute("title", title);
			request.setAttribute("h1", h1_text);
			request.setAttribute("url", "./AddressList");
			request.setAttribute("btnValue", "一覧表示");
			request.getRequestDispatcher("./jsp/result.jsp").forward(request, response);
		}
	}

	//Update処理
	private boolean updateProcess(HttpServletRequest request){
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		if((name.equals("") && address.equals("") && tel.equals(""))|| tel.length() != 10){
			return false;
		}else{
			conn = DatabaseHelper.getConnectionInstance();
			StringBuffer query = new StringBuffer("update " + DatabaseHelper.TABLE_NAME + " set ");

			if(!name.equals("")) query.append("name=").append("'"+name+"'").append(",");
			if(!address.equals("")) query.append("address=").append("'"+address+"'").append(",");
			if(!tel.equals("")) query.append("tel=").append("'"+tel+"'").append(",");

			query = query.deleteCharAt(query.lastIndexOf(",")); //末尾の「,]を削除
			query.append(" where id=?");

			try {
				stmt = conn.prepareStatement(new String(query));
				stmt.setInt(1, Integer.parseInt(request.getParameter("id")));
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				error = true;
				return false;
			}finally{
				commonClose();
			}
			return true;
		}
	}

	//return "氏名：○○○ 住所：△△△ 電話番号：□□□"
	private String getDispAddressData(int id){
		conn = DatabaseHelper.getConnectionInstance();
		String returnStr = "";
		try {
			stmt = conn.prepareStatement("select name,address,tel from " + DatabaseHelper.TABLE_NAME + " where id=?");
			stmt.setInt(1,id);
			ResultSet result = stmt.executeQuery();
			result.next();
			returnStr = "氏名  ：  " + result.getString("name") + "  住所  :  " + result.getString("address") + "  電話番号  :  " + result.getString("tel");
			commonClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnStr;
	}

	//共通close処理
	private void commonClose(){
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
