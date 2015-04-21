package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * データベースに関する補助処理等を記述
 */

public class DatabaseHelper {
    private final String URL = "jdbc:mysql://localhost/";
    private final String DB_NAME = "addressbook";
    private final String TABLE_NAME = "user_mst";
    private final String USER = "root";
    private final String PASSWORD = "T@kao521";

    //作業するテーブルの項目名を格納
    public class Columns{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String ADDRESS = "address";
        public static final String TEL = "tel";
    }

    //クエリのタイプを格納
    public class Query{
        public static final int SELECT = 0;
        public static final int INSERT = 1;
        public static final int UPDATE = 2;
        public static final int DELETE = 3;
    }

    private StringBuffer query;  //クエリ格納StringBuffer
    private Connection conn;     //データベース接続用Connection
    private ResultSet resultSet; //クエリ実行結果格納
    private PreparedStatement preStmt; //クエリ実行用
    private ArrayList<HashMap<String,String>> list; //結果格納ArrayList
    /*
     * ジェネリックスにHashMap<String,String>を採用したのは、
     * 指定したキー値で、適切な値を取得するため
     */

    public DatabaseHelper(){
        query = new StringBuffer();
        conn = null;
        resultSet = null;
        preStmt = null;
        list = null;
    }

    /*
     * 指定されたクエリタイプから、クエリを実行する
     *  引数 id がInteger型なのはnullを許容するため
     * return値 : boolean
     *   全ての処理が正しく行われれば、true
     *   途中でエラー等が発生した場合は、false
    */
    public boolean excuteSQL(int queryType,Integer id,String name,String address,String tel){
        boolean result = false;
        if(!connectDatabase()) return false; //DBに接続できなければ処理中断
        switch(queryType){
        case Query.SELECT:
            result = select(id);
            break;
        case Query.INSERT:
            result = insert(name,address,tel);
            break;
        case Query.UPDATE:
            result = update(id,name,address,tel);
            break;
        case Query.DELETE:
            result = delete(id);
            break;
        default:
            break;
        }
        if(result){
            commit();
        }else{
            rollback();
        }
        close();
        return true;
    }

    /*
     * クエリ実行後の結果をArrayListとして返す。
     */
    public ArrayList<HashMap<String,String>> getResultHashMap(){
        return this.list;
    }

    //ArryaListへ結果をセット
    private void setResultArrayList(){
        list = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> workerMap; //作業用HashMap
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    workerMap = new HashMap<String,String>();
                    workerMap.put(Columns.ID, resultSet.getString(Columns.ID));
                    workerMap.put(Columns.NAME, resultSet.getString(Columns.NAME));
                    workerMap.put(Columns.ADDRESS,resultSet.getString(Columns.ADDRESS));
                    workerMap.put(Columns.TEL, resultSet.getString(Columns.TEL));
                    list.add(workerMap);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    //DBに接続
    private boolean connectDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL + DB_NAME , USER , PASSWORD);
            conn.setAutoCommit(false); //自動コミットOFF
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Select処理
    private boolean select(Integer id){
        query.append("SELECT * FROM ").append(TABLE_NAME);

        //引数がnullでなければ、そのIDで条件を付加する
        if(id != null) query.append(" WHERE id=").append(id);

        try {
            preStmt = conn.prepareStatement(query.toString());
            resultSet = preStmt.executeQuery();
            this.setResultArrayList();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Insert処理
    private boolean insert(String name,String address,String tel){
        query.append("INSERT INTO ").append(TABLE_NAME).append(" (");
        query.append(Columns.NAME).append(",");
        query.append(Columns.ADDRESS).append(",");
        query.append(Columns.TEL).append(") ");
        query.append("VALUES (?,?,?)");
        try {
            preStmt = conn.prepareStatement(query.toString());
            preStmt.setString(1, name);
            preStmt.setString(2, address);
            preStmt.setString(3, tel);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Update処理
    private boolean update(int id,String name,String address,String tel){
        query.append("UPDATE ").append(TABLE_NAME).append(" SET ");

        if(name != null && !name.equals(""))
            query.append(Columns.NAME).append("='" + name + "'").append(",");

        if(address != null && !address.equals(""))
            query.append(Columns.ADDRESS).append("='" + address + "'").append(",");

        if(tel != null && !tel.equals(""))
            query.append(Columns.TEL).append("='" + tel + "'").append(",");

        query = query.deleteCharAt(query.lastIndexOf(",")); //末尾の "," を削除する(必ず付加されているため）
        query.append(" WHERE id=?");

        try{
            preStmt = conn.prepareStatement(query.toString());
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Delete処理
    private boolean delete(int id){
        query.append("DELETE FROM ").append(TABLE_NAME).append(" WHERE id=?");
        try{
            preStmt = conn.prepareStatement(query.toString());
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //コミット処理
    private void commit(){
        try {
            if(conn != null) conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ロールバック処理
    private void rollback(){
        try {
            if(conn != null) conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Connection と PrepaerdStatement インスタンスを .close()
    private void close(){
        if(preStmt != null){
            try {
                preStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
