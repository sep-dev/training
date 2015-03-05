// 登録フォームの入力チェック
// 入力がされていない場合、選択されていない場合にアラートが表示される
function check_form(f){
	var error_message = "";

	// 名前が空欄だった場合
	if (f.name.value == "") {
		error_message += "名前を入力してください。\n";
		}

	// 名前に記号等が使われた場合
	if (f.name.value.match(/[^ぁ-んァ-ヶー一-龠-a-zA-Z \s]+/)) {
		error_message += "名前は平仮名・カタカナ・漢字・半角英字で入力してください。\n";
		}

	// 性別が選択されていない場合
	if (f.sex.value == "") {
		error_message += "性別を選択してください。\n";
		}

	// 年齢が空欄だった場合
	if (f.age.value == "") {
		error_message += "年齢を入力してください。\n";
		}

	// 年齢に数値以外が入力された場合
	if (f.age.value.match(/[^0-9|-]+/)) {
		error_message += "年齢は数値を入力してください。\n";
		}

	// 利き手が選択されていない場合
	if (f.hand.value == "") {
		error_message += "利き手を選択してください。\n";
		}

	// 身長が空欄だった場合
	if (f.height.value == "") {
		error_message += "身長を入力してください。\n";
		}

	// 身長に数値以外が入力された場合
	if (f.height.value.match(/[^0-9|-]+/)) {
		error_message += "身長は数値を入力してください。\n";
		}

	// 体重が空欄だった場合
	if (f.weight.value == "") {
		error_message += "体重を入力してください。\n";
		}

	// 体重に数値以外が入力された場合
	if (f.weight.value.match(/[^0-9|-]+/)) {
		error_message += "体重は数値を入力してください。\n";
		}

// 上記どれかが当てはまった場合の処理
	if (error_message != "") {
		error_message = "入力内容に誤りがあります。\n以下の内容を確認してください。\n\n" + error_message;

			alert(error_message);

		return false;
		}
		return true;
	}