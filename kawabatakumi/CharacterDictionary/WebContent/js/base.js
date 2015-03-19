// 登録フォームの入力チェック
// 入力がされていない場合、選択されていない場合にアラートが表示される
  function check_form(){
    var error_message = "";

    // 名前への入力が空欄、指定外の記号を含んだ場合
    if (document.form.name.value.match(/[^ぁ-んァ-ヶー･・亜-龠a-zA-Z \s]$|^.{0}$/)) {
      error_message += "名前は平仮名・カタカナ・漢字・半角英字で入力してください。\n";
    }

    // 性別が選択されていない場合
    if (document.form.sex.value == "") {
      error_message += "性別を選択してください。\n";
    }

    // 年齢への入力が空欄、3桁以上、数字以外だった場合
    if (document.form.age.value.match(/[^{3}]$|[^0-9]$|^.{0}$/)) {
      error_message += "年齢は3桁以内の数値で入力してください。\n";
    }

    // 利き手が選択されていない場合
    if (document.form.hand.value == "") {
      error_message += "利き手を選択してください。\n";
    }

    // 身長への入力が空欄、3桁.1桁以上、数字以外だった場合
    if (document.form.height.value.match(/[^{3}]$|[^\.]$|[^{5}]$|[^0-9]$|^.{0}$/)) {
      error_message += "身長は3桁.1桁以内の数値で入力してください。\n";
    }

    // 体重への入力が空欄、3桁.1桁以上、数字以外だった場合
    if (document.form.weight.value.match(/[^{3}]$|[^\.]$|[^{5}]$|[^0-9]$|^.{0}$/)) {
      error_message += "体重は3桁.1桁以内の数値で入力してください。\n";
    }

    // 備考に指定外の記号が使われた場合
    if (document.form.note.value.match(/[^ぁ-んァ-ヶー･・亜-龠a-zA-Z、-！…-↓ \s \n]$/)) {
      error_message += "備考は平仮名・カタカナ・漢字・半角英字で入力してください。\n";
    }

    // 上記どれかが当てはまった場合の処理
    if (error_message != "") {
      error_message = "入力内容に誤りがあります。\n以下の内容を確認してください。\n\n" + error_message;
      alert(error_message);

      return false;
    }
      return true;
    }