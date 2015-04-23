package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;
import helper.ResultIds;
import helper.Urls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    private Connection conn = null;
    private PreparedStatement stmt = null;

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
            boolean insertResult = insert(
                    HtmlHelper.encording(request.getParameter(DatabaseHelper.ColumnNames.NAME)),
                    HtmlHelper.encording(request.getParameter(DatabaseHelper.ColumnNames.ADDRESS)),
                    HtmlHelper.encording(request.getParameter(DatabaseHelper.ColumnNames.TEL))
                );
            request.setAttribute(ResultIds.TITLE, insertResult ? "登録成功" : "登録失敗");
            request.setAttribute(ResultIds.H1＿TEXT, insertResult ? "登録しました！！" : "登録できませんでした");
            request.setAttribute(ResultIds.URL, insertResult ? Urls.ADDRESS_LIST : Urls.TOP);
            request.setAttribute(ResultIds.BUTTON_TEXT, insertResult ? "一覧表示" : "新規登録");
        }

        request.getRequestDispatcher("./jsp/" + Urls.RESULT).forward(request, response); //リザルトページへ遷移
    }

    /*
     * Insert処理
     * return 成功：true 失敗：false
     */
    private boolean insert(String name,String address,String tel){
        conn = DatabaseHelper.getConnectionInstance();
        try {
            stmt = conn.prepareStatement("INSERT INTO " + DatabaseHelper.TABLE_NAME + " (name,address,tel) VALUES (?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, tel);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }finally{
            DatabaseHelper.commonClose(conn, stmt);
        }
        return true;
    }

    /*
     * 入力値検査
     * ・受け取った値が空白ではないか
     * ・電話番号が入力された場合、番号は10桁 or 11桁であるか
     */
    private boolean checkValues(HttpServletRequest request){
        if(request.getParameter(DatabaseHelper.ColumnNames.NAME).equals("")) return false;
        if(request.getParameter(DatabaseHelper.ColumnNames.ADDRESS).equals("")) return false;
        if(request.getParameter(DatabaseHelper.ColumnNames.TEL).equals("") ||
                (request.getParameter(DatabaseHelper.ColumnNames.TEL).length() < 10 &&
                 request.getParameter(DatabaseHelper.ColumnNames.TEL).length() > 11)) return false;
        return true;
    }

}
