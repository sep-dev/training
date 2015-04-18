package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Select")
public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォワード先
		String forwardPath = null;

		// フォームに入力された情報を取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");		// 会員のID

		// 入力エラーのチェック
		boolean error = false;
		if(id == null) {
			// 会員が選択されていなければエラー
			error = true;
		}

		// 会員が選択されていれば
		if(error == false) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);		// 選択した会員のIDをセッションスコープに保存

			// フォワード先に, 「更新フォーム」のJSPファイルを設定
			forwardPath = "/WEB-INF/jsp/updateForm.jsp";
		}

		// 会員が選択されていなければ
		else {
			// フォワード先に, 「一覧表示」のJSPファイルを設定
			forwardPath = "/WEB-INF/jsp/list.jsp";		// 選択し直し
		}

		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}