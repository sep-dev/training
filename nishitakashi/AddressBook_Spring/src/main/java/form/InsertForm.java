package form;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登録画面でのデータ受け渡し用クラス
 * @param name 会員名
 * @param address 会員住所
 * @param tel 会員電話番号
 * @param radio1 ラジオボタンで選択した会員ID
 */

public class InsertForm implements Serializable,Form{
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @Id
    private String radio1;
    @Digits(integer=12, fraction = 0)
    private String tel;

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

    public String getRadio1(){
        return radio1;
    }
    public void setRadio1(String radio1){
        this.radio1=radio1;
    }
}

