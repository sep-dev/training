package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;
import helper.ResultIds;
import helper.Urls;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */

/*
 * データ削除を行う
 */

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseHelper db = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding(HtmlHelper.ENCORDING);
        response.setContentType("text/html; charset=" + HtmlHelper.ENCORDING);

        db = new DatabaseHelper();
        int id = Integer.parseInt(request.getParameter(DatabaseHelper.Column.ID)); //id取得

        boolean result = db.excuteSQL(DatabaseHelper.Query.DELETE, id, null, null, null); //削除処理

        request.setAttribute(ResultIds.TITLE, result ? "削除完了" : "削除失敗");
        request.setAttribute(ResultIds.H1＿TEXT, result ?  "削除しました！！" : "削除に失敗しました");
        request.setAttribute(ResultIds.URL, Urls.ADDRESS_LIST);
        request.setAttribute(ResultIds.BUTTON_TEXT, "一覧表示");
        request.getRequestDispatcher("./jsp/" + Urls.RESULT).forward(request, response); //リザルトページへ遷移
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(Urls.TOP); //ダイレクトに入った場合、トップページへ強制遷移
    }

}
