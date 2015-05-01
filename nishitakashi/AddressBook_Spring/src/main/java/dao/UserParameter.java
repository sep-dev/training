package dao;

import org.springframework.stereotype.Service;

/**
 * 選択した会員の情報を格納しておくクラス
 * @param id 会員ID
 * @param name 会員名
 * @param address 会員住所
 * @param tel 会員電話番号
 */
@Service
public class UserParameter {

    private String id;
    private String name;
    private String address;
    private String tel;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getTel(){
        return tel;
    }
    public void setTel(String tel){
        this.tel=tel;
    }
}
