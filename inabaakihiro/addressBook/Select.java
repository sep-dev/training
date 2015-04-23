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


	// 「一覧表示画面」で更新or削除ボタンが押された場合
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 選択された会員のIDを取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");

		// エラーチェック
		boolean error = false;
		if(id == null) {

			error = true;	// 会員が選択されていなければエラー
		}

		// ↓ エラーがなければ ↓
		if(error == false) {

			// 選択した会員のIDをセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("id", id);

			// 「更新フォーム」にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateForm.jsp");
			dispatcher.forward(request, response);
		}

		// ↓ エラーがあれば ↓
		else {

			// 「一覧表示」にリダイレクト
			response.sendRedirect("/addressBook/list.jsp");		// 選択し直し
		}
	}

}