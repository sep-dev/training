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

			// 「更新フォーム」にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateForm.jsp");
			dispatcher.forward(request, response);
		}

		// 会員が選択されていなければ
		else {
			// 「一覧表示」にリダイレクト
			response.sendRedirect("/addressBook/list.jsp");		// 選択し直し
		}
	}
}