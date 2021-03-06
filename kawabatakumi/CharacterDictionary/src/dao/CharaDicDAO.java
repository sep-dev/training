package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.CharaDic;
import dao.dbLoad;

import java.util.List;

/**
 * Servlet implementation class CharaDicDAO
 */
@WebServlet("/CharaDicDAO")
public class CharaDicDAO extends HttpServlet {
private static final long serialVersionUID = 1L;

/**
 * @see HttpServlet#HttpServlet()
 */

public List<CharaDic> findAll(){

Connection conn = null;
List<CharaDic> charaList = new ArrayList<CharaDic>();

try{
/* DBを検索し表示 */

// JDBCドライバを読み込み
//Class.forName("com.mysql.jdbc.Driver");
Class.forName(dbLoad.DriverNAME);

// データベースへ接続
conn = DriverManager.getConnection(
dbLoad.Url,dbLoad.User,dbLoad.Pass);

// SELECT文を準備
String sql = "SELECT Id, Name, Age, Hand, Height, Weight, Sex FROM book01";

// プリペアドステートメントの用意
PreparedStatement pStmt = conn.prepareStatement(sql);

// SELECTを実行し、結果表を取得
ResultSet rs = pStmt.executeQuery();

// 結果表に格納されたレコードの内容を
// CharaDicDaoインスタンスに設定し、ArrayListインスタンスに追加
while (rs.next()) {
int id = rs.getInt("Id");
String name = rs.getString("Name");
int age = rs.getInt("Age");
String hand = rs.getString("Hand");
String height = rs.getString("Height");
String weight = rs.getString("Weight");
String sex = rs.getString("Sex");
CharaDic charadic = new CharaDic(id, name, age, hand, height, weight, sex);
charaList.add(charadic);
}


} catch(SQLException e) {
e.printStackTrace();
return null;
} catch (ClassNotFoundException e) {
e.printStackTrace();
return null;
} finally {
// データベース切断
if (conn != null) {
try{
conn.close();
}catch (SQLException e) {
e.printStackTrace();
return null;
}
}
}
return charaList;
}

public boolean model(String name,int age, String hand, String height, String weight, String sex){

try{
/* レコードの挿入 */

// JDBCドライバを読み込み
Class.forName(dbLoad.DriverNAME);

// データベースへ接続
Connection conn = DriverManager.getConnection(
dbLoad.Url,dbLoad.User,dbLoad.Pass);

// ステートメントの用意
Statement st = conn.createStatement();

// INSERT文の準備
String sql = "INSERT INTO book01 (Name, Age, Hand, Height, Weight, Sex) VALUES('"+ name +"', '"+ age +"', '"+ hand +"', '"+ height +"', '"+ weight +"', '"+ sex +"')";
PreparedStatement pStmt = conn.prepareStatement(sql);

int result = pStmt.executeUpdate();

// データベース切断
st.close();
conn.close();
pStmt.close();

if(result != 1) {
return false;
}


}catch(ClassNotFoundException e){
e.printStackTrace();
return false;
}catch (SQLException e) {
e.printStackTrace();
}
return true;


}


public CharaDic model2(int Id) {

try{
/* データの格納 */

// JDBCドライバを読み込み
Class.forName(dbLoad.DriverNAME);

// データベースへ接続
Connection conn = DriverManager.getConnection(
dbLoad.Url,dbLoad.User,dbLoad.Pass);

// ステートメントの用意
Statement st = conn.createStatement();

// SELECT文を準備
String sql = "SELECT Id, Name, Age, Hand, Height, Weight, Sex FROM book01 where Id = "+ Id;

// プリペアドステートメントの用意
PreparedStatement pStmt = conn.prepareStatement(sql);

// SELECTを実行し、結果表を取得
ResultSet rs = pStmt.executeQuery();

// Idに一致する一行文をDTOモデルに入れる
rs.next();
int id = rs.getInt("Id");
String name = rs.getString("Name");
int age = rs.getInt("Age");
String hand = rs.getString("Hand");
String height = rs.getString("Height");
String weight = rs.getString("Weight");
String sex = rs.getString("Sex");
CharaDic charadic = new CharaDic(id, name, age, hand, sex ,height , weight);

// データベースを切断
rs.close();
st.close();
conn.close();

return charadic;

}catch(ClassNotFoundException e){
return null;
}catch(SQLException e){
return null;
}
}

public int update(String Name,int Age, String Hand, String Height, String Weight, String Sex, int charaids) {

try{
/* データの更新 */

// JDBCドライバを読み込み
Class.forName(dbLoad.DriverNAME);

// データベースへ接続
Connection conn = DriverManager.getConnection(
dbLoad.Url,dbLoad.User,dbLoad.Pass);

// ステートメントの用意
Statement st = conn.createStatement();

// UPDATE文の準備
String sql = "UPDATE book01 set name ='"+ Name +"', age ='"+ Age +"', hand ='"+ Hand +"', height ='"+ Height +"', weight ='"+ Weight +"', sex ='"+ Sex +"' where id = "+charaids;
int upd =st.executeUpdate(sql);

// データベース切断
st.close();
conn.close();

return upd;

}catch(ClassNotFoundException e){
return 0;
}catch (SQLException e) {
return 0;
}
}

public int end(int charaids) {

try{
/* DBにあるデータの削除処理 */

// JDBCドライバを読み込み
Class.forName(dbLoad.DriverNAME);

// データベースへ接続
Connection conn = DriverManager.getConnection(
dbLoad.Url,dbLoad.User,dbLoad.Pass);

// ステートメントの用意
Statement st = conn.createStatement();

// DELETE文の準備
String sql = "DELETE from book01 where id = " +charaids;
int upd =st.executeUpdate(sql);

// データベース切断
st.close();
conn.close();

return upd;

}catch(ClassNotFoundException e){
return 0;
}catch(SQLException e){
return 0;
}
}
}



