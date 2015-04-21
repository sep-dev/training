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
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseHelper db = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(Urls.TOP);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=" + HtmlHelper.ENCORDING);
        response.setCharacterEncoding(HtmlHelper.ENCORDING);

        if(!checkValues(request)){
            request.setAttribute(ResultIds.TITLE, "登録失敗");
            request.setAttribute(ResultIds.H1＿TEXT, "空欄を埋めてください！");
            request.setAttribute(ResultIds.URL, Urls.TOP);
            request.setAttribute(ResultIds.BUTTON_TEXT, "新規登録");
        }else{
            String name = HtmlHelper.encording(request.getParameter(DatabaseHelper.Columns.NAME));
            String address = HtmlHelper.encording(request.getParameter(DatabaseHelper.Columns.ADDRESS));
            String tel = HtmlHelper.encording(request.getParameter(DatabaseHelper.Columns.TEL));

            db = new DatabaseHelper();
            boolean insertResult = db.excuteSQL(DatabaseHelper.Query.INSERT, null, name, address, tel);

            request.setAttribute(ResultIds.TITLE, insertResult ? "登録成功" : "登録失敗");
            request.setAttribute(ResultIds.H1＿TEXT, insertResult ? "登録しました！！" : "登録できませんでした");
            request.setAttribute(ResultIds.URL, insertResult ? Urls.ADDRESS_LIST : Urls.TOP);
            request.setAttribute(ResultIds.BUTTON_TEXT, insertResult ? "一覧表示" : "新規登録");
        }
        request.getRequestDispatcher("./jsp/" + Urls.RESULT).forward(request, response); //リザルトページへ遷移
    }

    /*
     * 入力値検査
     * ・受け取った値が空白ではないか
     * ・電話番号が入力された場合、番号は10桁 or 11桁であるか
     */
    private boolean checkValues(HttpServletRequest request){
        if(request.getParameter(DatabaseHelper.Columns.NAME).equals("")) return false;
        if(request.getParameter(DatabaseHelper.Columns.ADDRESS).equals("")) return false;
        if(request.getParameter(DatabaseHelper.Columns.TEL).equals("") ||
                (request.getParameter(DatabaseHelper.Columns.TEL).length() < 10 &&
                 request.getParameter(DatabaseHelper.Columns.TEL).length() > 11)) return false;
        return true;
    }

}
