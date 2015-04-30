package form;

import javax.validation.constraints.NotNull;

/**
 * 一覧選択画面でのデータ受け渡し用クラス
 * @param radio1 ラジオボタンで選択した会員ID
 */
public class SelectForm{

    @NotNull
    private String radio1;

    public String getRadio1(){
        return radio1;
    }
    public void setRadio1(String radio1){
        this.radio1=radio1;
    }

}
