package main;

import helper.DatabaseHelper;
import helper.HtmlHelper;
import helper.ResultIds;
import helper.Urls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
    private DatabaseHelper db = null;

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
            request.setAttribute("id", request.getParameter(DatabaseHelper.Column.ID));
            request.setAttribute("addressData",getDispAddressData(Integer.parseInt(request.getParameter(DatabaseHelper.Column.ID))));
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
        Integer id = new Integer(request.getParameter(DatabaseHelper.Column.ID));
        String name = HtmlHelper.encording(request.getParameter(DatabaseHelper.Column.NAME));
        String address = HtmlHelper.encording(request.getParameter(DatabaseHelper.Column.ADDRESS));
        String tel = HtmlHelper.encording(request.getParameter(DatabaseHelper.Column.TEL));

        if(name.equals("") && address.equals("") && tel.equals("")) return false;
        if(!tel.equals("") && (tel.length() <= 9 || tel.length() >= 12)) return false;

        db = new DatabaseHelper();
        return db.excuteSQL(DatabaseHelper.Query.UPDATE, id, name, address, tel);
    }


    /*
     *  引数のIDを保持するデータを取得する
     *  return "氏名：○○○ 住所：△△△ 電話番号：□□□"
     */
    private String getDispAddressData(int id){
        StringBuffer resultStr = new StringBuffer();
        db = new DatabaseHelper();
        if(db.excuteSQL(DatabaseHelper.Query.SELECT, id, null, null, null)){
            ArrayList<HashMap<String,String>> map = db.getResultHashMap();
            for(HashMap<String,String> row : map){
                resultStr.append("氏名: ").append(row.get(DatabaseHelper.Column.NAME));
                resultStr.append("   住所 : ").append(row.get(DatabaseHelper.Column.ADDRESS));
                resultStr.append("   電話番号 : ").append(row.get(DatabaseHelper.Column.TEL));
            }
        }
        return resultStr.toString();

    }
}
