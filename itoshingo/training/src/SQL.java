import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SQL {

	protected Connection connect(){

		java.sql.Connection con = null;

		try{
			// ドライバクラスをロード
			Class.forName("com.mysql.jdbc.Driver");
            // データベースへ接続
			con = DriverManager.getConnection("jdbc:mysql://localhost/address","root","zxcASDqwe");
			return con;
		}catch(Exception e){
			return null;
		}
	}

	protected boolean insert(Connection con,String name,String address,String tel){
		PreparedStatement ps = null;
		try{
			String sql = "insert into tbaddress(name,address,tel) value(?,?,?)";
	        // ステートメントオブジェクトを生成
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
	        ps.setString(2,address);
	        ps.setString(3,tel);

	        int result = ps.executeUpdate();

	        ps.close();
	        con.close();
			return true;

		}catch(Exception e){
			return false;
		}
	}

	protected boolean update(Connection con,String id,String name,String address,String tel){

		PreparedStatement ps = null;

		try{
			String sql = "update tbaddress set name = ?,address = ?,tel = ? where id = ?";

        	ps = con.prepareStatement(sql);

        	ps.setString(1,name);
        	ps.setString(2,address);
        	ps.setString(3,tel);
        	ps.setString(4,id);

        	int result = ps.executeUpdate();

        	ps.close();
        	con.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}

	protected boolean delete(Connection con,String id){
		try{
			PreparedStatement ps = null;

			String sql = "delete from tbaddress where id=?";
			ps = con.prepareStatement(sql);

			ps.setString(1,id);

			int result = ps.executeUpdate();

			ps.close();
			con.close();

			return true;
		}catch(Exception e){
			return false;
		}
	}

	protected ResultSet select(Connection con,String id){

		if(id == null){
		try{
			PreparedStatement ps = null;
			String sql = "select * from tbaddress";
			ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			return rs;
		}catch(Exception e){
			return null;
		}
		}else{
			try{
				PreparedStatement ps = null;

				String sql = "select * from tbaddress where id="+ id;

				ps = con.prepareStatement(sql);

				ResultSet rs = ps.executeQuery();

				return rs;
			}catch(Exception e){
				return null;
			}
		}
	}
}
