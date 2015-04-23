package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DatabaseLogic;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォワード先
		String forwardPath = null;

		// フォームに入力された情報を取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		// 入力エラーのチェック
		boolean error = false;
		if(name == "" || address == "" || tel == "") {
			// 何も入力されてない入力欄があればエラー
			error = true;
		}

		// 入力エラーがなければ
		if(error == false) {
			DatabaseLogic dbLogic = new DatabaseLogic();
			dbLogic.connect();

			// 既存IDの最大値に, +1した値を新規会員のIDにする
			String[][] maxId = dbLogic.executeSQL("SELECT MAX(ID) FROM ADDRESS_TBL");
			int id = Integer.parseInt(maxId[0][0]) + 1;

			// 新規会員をデータベースに登録
			dbLogic.executeSQL("INSERT INTO ADDRESS_TBL(ID,NAME,ADDRESS,TEL) VALUES("
					+ id + ",\"" + name + "\",\"" + address + "\",\"" + tel + "\")");

			// フォワード先に, 「登録成功」のJSPファイルを設定
			forwardPath = "/WEB-INF/jsp/registerSuccess.jsp";
		}

		// 入力エラーがあれば
		else {
			// フォワード先に, 「登録失敗」のJSPファイルを設定
			forwardPath = "/WEB-INF/jsp/registerFailure.jsp";
		}

		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}