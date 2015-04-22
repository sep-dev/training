package action;

import java.util.List;
import model.CharaDic;
import dao.CharaDicDAO;

public class data_insert {

  public static void main(String[] args) {

    // テーブルの全レコードを取得
    CharaDicDAO CharaDAO = new CharaDicDAO();
    List<CharaDic> charaList = CharaDAO.findAll();

    // 取得したレコードの内容を表示
    for(CharaDic chara : charaList) {
        System.out.println("ID:" + chara.getId());
        System.out.println("NAME:" + chara.getName());
    }
  }

}
