package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SwitchMenu")
public class SwitchMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォワード先
		String redirectPath = null;

		String action = request.getParameter("action");

		if(action == null) {
			// リダイレクト先に, 「新規登録」のJSPファイルを設定
			redirectPath = "/addressBook/";
		}

		else if(action.equals("list")) {
			// リダイレクト先に, 「一覧表示」のJSPファイルを設定
			redirectPath = "/addressBook/list.jsp";
		}

		// 設定されたリダイレクト先にリダイレクト
		response.sendRedirect(redirectPath);
	}
}