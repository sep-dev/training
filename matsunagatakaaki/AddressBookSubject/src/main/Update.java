package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;
import helper.ResultIds;
import helper.Urls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection conn = null;
    private PreparedStatement stmt = null;

    private String title,h1_text; //結果画面での表示文字列格納
    private boolean error = false; //更新処理途中での、例外発生&失敗フラグ

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
        response.setCharacterEncoding(HtmlHelper.ENCORDING);
        response.setContentType("text/html; charset=" + HtmlHelper.ENCORDING);

        if(!HtmlHelper.searchParameter("update", request)){
            //パラメータ名に "update" が存在しなければ、アップデートする内容を入力するページへ遷移させる
            request.setAttribute("id", request.getParameter(DatabaseHelper.ColumnNames.ID));
            request.setAttribute("addressData",getDispAddressData(Integer.parseInt(request.getParameter(DatabaseHelper.ColumnNames.ID))));
            request.getRequestDispatcher("./jsp/" + Urls.UPDATE_INPUT).forward(request, response);
        }else{
            if(updateProcess(request)){
                title = "更新成功";
                h1_text = "更新に成功しました！！";
            }else{
                title = "更新失敗";
                h1_text = error ? "更新に失敗しました。" : "空欄です。入力してください！";
            }
            request.setAttribute(ResultIds.TITLE, title);
            request.setAttribute(ResultIds.H1＿TEXT, h1_text);
            request.setAttribute(ResultIds.URL, Urls.ADDRESS_LIST);
            request.setAttribute(ResultIds.BUTTON_TEXT, "一覧表示");
            request.getRequestDispatcher("./jsp/" + Urls.RESULT).forward(request, response);
        }
    }

    //Update処理
    private boolean updateProcess(HttpServletRequest request){
        String name = HtmlHelper.encording(request.getParameter(DatabaseHelper.ColumnNames.NAME));
        String address = HtmlHelper.encording(request.getParameter(DatabaseHelper.ColumnNames.ADDRESS));
        String tel = HtmlHelper.encording(request.getParameter(DatabaseHelper.ColumnNames.TEL));

        if(name.equals("") && address.equals("") && tel.equals("")) return false;
        if(!tel.equals("") && (tel.length() <= 9 || tel.length() >= 12)) return false;

        conn = DatabaseHelper.getConnectionInstance();
        StringBuffer query = new StringBuffer("UPDATE " + DatabaseHelper.TABLE_NAME + " SET ");

        if(!name.equals("")) query.append(DatabaseHelper.ColumnNames.NAME).append("='" + name + "'").append(",");
        if(!address.equals("")) query.append(DatabaseHelper.ColumnNames.ADDRESS).append("='" + address + "'").append(",");
        if(!tel.equals("")) query.append(DatabaseHelper.ColumnNames.TEL).append("='" + tel + "'").append(",");

        query = query.deleteCharAt(query.lastIndexOf(",")); //末尾の "," を削除する(必ず付加されているため）
        query.append(" WHERE id=?");

        try {
            stmt = conn.prepareStatement(new String(query));
            stmt.setInt(1, Integer.parseInt(request.getParameter(DatabaseHelper.ColumnNames.ID)));
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            error = true;
            return false;
        }finally{
            DatabaseHelper.commonClose(conn, stmt);
        }
        return true;
    }


    /*
     *  引数のIDを保持するデータを取得する
     *  return "氏名：○○○ 住所：△△△ 電話番号：□□□"
     */
    private String getDispAddressData(int id){
        conn = DatabaseHelper.getConnectionInstance();
        StringBuffer returnStr = new StringBuffer(); //生成文字列格納StringBuffer
        try {
            stmt = conn.prepareStatement("SELECT name,address,tel FROM " + DatabaseHelper.TABLE_NAME + " WHERE id=?");
            stmt.setInt(1,id);
            ResultSet result = stmt.executeQuery();
            result.first(); //最初に移動
            returnStr.append("氏名 : ").append(result.getString(DatabaseHelper.ColumnNames.NAME));
            returnStr.append("  住所 : ").append(result.getString(DatabaseHelper.ColumnNames.ADDRESS));
            returnStr.append("  電話番号 : ").append(result.getString(DatabaseHelper.ColumnNames.TEL));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DatabaseHelper.commonClose(conn, stmt);
        }
        return returnStr.toString();
    }
}
