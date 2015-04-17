package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		String forwardPath = null;

		String action = request.getParameter("action");

		if(action == null) {
			// フォワード先に, 「新規登録」のJSPファイルを設定
			forwardPath = "/index.jsp";
		}

		else if(action.equals("list")) {
			// フォワード先に, 「一覧表示」のJSPファイルを設定
			forwardPath = "/WEB-INF/jsp/list.jsp";
		}

		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);

	}
}