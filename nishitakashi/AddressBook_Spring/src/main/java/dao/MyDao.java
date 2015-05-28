package dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import form.Form;
/**
 * データベースにアクセス後、行われる処理を集めたクラス
 * @param jdbcTemplate コントローラで作成したjdbcTemplateを格納
 */
@Service
public class MyDao {
     @Autowired
     private JdbcTemplate jdbcTemplate;
    /**
     * データベースから会員情報を全件選択
     */
    public List<Map<String, Object>> selectAll() {
        List<Map<String, Object>>  list = jdbcTemplate.queryForList("select * from tbAddress");
        return list;
    }

    /**
     * 1件選択した会員情報を保持
     */

    public void selectRadio(Form selectForm,UserParameter user){
        String sql_name="SELECT name FROM tbAddress WHERE id = ?";
        String sql_adr="SELECT address FROM tbAddress WHERE id = ?";
        String sql_tel="SELECT tel FROM tbAddress WHERE id = ?";
        String rs_name=jdbcTemplate.queryForObject(sql_name, new Object[] {selectForm.getRadio1()}, String.class);
        String rs_adr=jdbcTemplate.queryForObject(sql_adr, new Object[] {selectForm.getRadio1()}, String.class);
        String rs_tel=jdbcTemplate.queryForObject(sql_tel, new Object[] {selectForm.getRadio1()}, String.class);
        user.setId(selectForm.getRadio1());
        user.setName(rs_name);
        user.setAddress(rs_adr);
        user.setTel(rs_tel);
    }

    /**
     * 会員情報のデータベースへの挿入
     */
    public void insert(Form model) {
        final String sql = "insert into tbAddress(name, address, tel) values(?, ?, ?)";
        jdbcTemplate .update(sql, new Object[]{model.getName(),
                                    model.getAddress(),
                                    model.getTel()});
    }

    /**
     * データベースの更新
     */
    public void update(Form model,UserParameter user) {
        final String sql = "update tbAddress set name=?, address=?, tel=? where id=? ;";
        jdbcTemplate .update(sql, new Object[]{model.getName(),
                                    model.getAddress(),
                                    model.getTel(),
                                    model.getRadio1()});
    }

    /**
     * データベースから選択したデータを削除
     */
    public void delete(UserParameter user) {
        final String sql = "delete from tbAddress where id=? ;";
        jdbcTemplate .update(sql, new Object[]{user.getId()});
    }
}
