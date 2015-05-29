package form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 一覧選択画面でのデータ受け渡し用クラス
 * @param radio1 ラジオボタンで選択した会員ID
 */
public class SelectForm implements Serializable,Form{
    @NotNull
    private String radio1;

    public String getRadio1(){
        return radio1;
    }
    public void setRadio1(String radio1){
        this.radio1=radio1;
    }
    @Override
    public String getName() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }
    @Override
    public void setName(String name) {
        // TODO 自動生成されたメソッド・スタブ

    }
    @Override
    public String getAddress() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }
    @Override
    public void setAddress(String address) {
        // TODO 自動生成されたメソッド・スタブ

    }
    @Override
    public String getTel() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }
    @Override
    public void setTel(String tel) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
