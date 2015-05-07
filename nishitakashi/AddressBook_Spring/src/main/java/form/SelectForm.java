package form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 一覧選択画面でのデータ受け渡し用クラス
 * @param radio1 ラジオボタンで選択した会員ID
 */
@Entity
@Table(name="tbAddress")
public class SelectForm implements Serializable{

    @NotNull
    @Column(name="id")
    private String radio1;

    public String getRadio1(){
        return radio1;
    }
    public void setRadio1(String radio1){
        this.radio1=radio1;
    }

}
