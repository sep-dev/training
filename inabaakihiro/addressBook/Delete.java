package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DatabaseLogic;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;


	// 「更新フォーム」で削除ボタンが押された場合
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 「削除確認画面」へ進む
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/deleteConfirm.jsp");
		dispatcher.forward(request, response);
	}


	// 「削除確認画面」で削除ボタンが押された場合
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// データベース接続
		DatabaseLogic dbLogic = new DatabaseLogic();
		dbLogic.connect();

		// 削除対象の会員のIDを取得
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		// 会員情報を、データベースから削除
		dbLogic.executeSQL("DELETE FROM ADDRESS_TBL WHERE ID = " + id);

		// データベース切断
		dbLogic.disconnect();

		// 「削除完了画面」へ進む
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/deleteComplete.jsp");
		dispatcher.forward(request, response);
	}

}