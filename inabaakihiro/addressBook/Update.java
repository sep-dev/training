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

@WebServlet("/Update")
public class Update extends HttpServlet {
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
		if(name == "" && address == "" && tel == "") {
			// 更新情報が, 1つも入力されていなければエラー
			error = true;
		}

		// 入力エラーがなければ
		if(error == false) {
			DatabaseLogic dbLogic = new DatabaseLogic();
			dbLogic.connect();

			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");

			// 入力があった項目を、更新対象に追加していく処理
			String updateColumn = "";

			if(name != "") {
				updateColumn += "NAME = \'" + name + "\'";
			}

			if(address != "") {
				if(updateColumn != "") { updateColumn += ", "; }	// 更新カラムが既にあれば、「,」で区切る

				updateColumn += "ADDRESS = \'" + address + "\'";
			}

			if(tel != "") {
				if(updateColumn != "") { updateColumn += ", "; }

				updateColumn += "TEL = \'" + tel + "\'";
			}

			// 選択されていた会員の、更新対象のデータを更新
			dbLogic.executeSQL("UPDATE ADDRESS_TBL SET " + updateColumn + " WHERE ID = " + id);

			dbLogic.disconnect();

			// フォワード先に, 「更新成功」のJSPファイルを設定
			forwardPath = "/WEB-INF/jsp/updateSuccess.jsp";
		}

		// 入力エラーがあれば
		else {
			// フォワード先に, 「更新失敗」のJSPファイルを設定
			forwardPath = "/WEB-INF/jsp/updateFailure.jsp";
		}

		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}