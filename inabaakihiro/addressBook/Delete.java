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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 「削除確認画面」にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/deleteConfirm.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DatabaseLogic dbLogic = new DatabaseLogic();
		dbLogic.connect();

		// 削除対象の会員のIDを取得
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		// 選択されていた会員の、更新対象のデータを更新
		dbLogic.executeSQL("DELETE FROM ADDRESS_TBL WHERE ID = " + id);

		dbLogic.disconnect();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/deleteComplete.jsp");
		dispatcher.forward(request, response);
	}
}