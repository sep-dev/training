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


	// 各ページで「一覧表示ボタン」または「新規登録ボタン」が押された時
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String redirectPath = null;		// リダイレクト先

		String action = request.getParameter("action");		// 送信された情報を取得


		// 「一覧表示」ボタンが押されたとき
		if(action == null) {

			// リダイレクト先に, 「新規登録」のJSPファイルを設定
			redirectPath = "/addressBook/";
		}


		// 「新規登録」ボタンが押されたとき
		else if(action.equals("list")) {

			// リダイレクト先に、「一覧表示」のJSPファイルを設定
			redirectPath = "/addressBook/list.jsp";
		}


		// 設定されたリダイレクト先にリダイレクト
		response.sendRedirect(redirectPath);
	}

}