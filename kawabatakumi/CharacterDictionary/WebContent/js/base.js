// 登録フォームの入力チェック
// 入力がされていない場合、選択されていない場合にアラートが表示される
  function check_form(){
    var error_message = "";

    // 名前が空欄だった場合
    if (document.form.name.value == "") {
      error_message += "名前を入力してください。\n";
    }

    // 名前に記号等が使われた場合
    if (document.form.name.value.match(/[^ぁ-んァ-ヶー･・亜-龠a-zA-Z \s]+/)) {
      error_message += "名前は平仮名・カタカナ・漢字・半角英字で入力してください。\n";
    }

    // 性別が選択されていない場合
    if (document.form.sex.value == "") {
      error_message += "性別を選択してください。\n";
    }

    // 年齢が空欄だった場合
    if (document.form.age.value == "") {
      error_message += "年齢を入力してください。\n";
    }

    // 年齢に3桁以上入力された場合
    if (document.form.age.value.match(/[^{0-3}]$/)) {
      error_message += "入力できる年齢は3ケタまでです。\n";
    }

    // 年齢に数値以外が入力された場合
    if (document.form.age.value.match(/[^0-9]$/)) {
      error_message += "年齢は数値を入力してください。\n";
    }

    // 利き手が選択されていない場合
    if (document.form.hand.value == "") {
      error_message += "利き手を選択してください。\n";
    }

    // 身長が空欄だった場合
    if (document.form.height.value == "") {
      error_message += "身長を入力してください。\n";
    }

    // 身長に000.0以上の入力がされた場合
    if (document.form.height.value.match(/[^{0-3},^\.,^{0-5}]$/)) {
      error_message += "身長は3ケタ.1ケタ以上は入力できません。\n";
    }

    // 身長に数値と\.以外が入力された場合
    if (document.form.height.value.match(/[^0-9]$/)) {
      error_message += "身長は数値を入力してください。\n";
    }

    // 体重が空欄だった場合
    if (document.form.weight.value == "") {
      error_message += "体重を入力してください。\n";
    }

    // 体重に000.0以上の入力がされた場合
    if (document.form.weight.value.match(/[^{0-3},^\.,^{0-5}]$/)) {
      error_message += "体重は3ケタ.1ケタ以上は入力できません。\n";
    }

    // 体重に数値以外が入力された場合
    if (document.form.weight.value.match(/[^0-9]$/)) {
      error_message += "体重は数値を入力してください。\n";
    }

    // 備考に記号等が使われた場合
    if (document.form.note.value.match(/[^ぁ-んァ-ヶー･・亜-龠a-zA-Z、-！…-↓ \s \n]+/)) {
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