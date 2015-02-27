// 登録フォームの入力チェック
function check_form(f){
var error_message = "";
// 必須チェック
if (f.name.value.match( /[^ぁ-んァ-ン \s]+/ ) ) {
error_message += "名前を入力してください。\n"; }

if (f.sex.value == "") {
error_message += "性別を選択してください。\n"; }

// 年齢チェック
if (f.age.value.match(/[^0-9|-]+/)) {
error_message += "年齢は数値を入力してください。\n"; }

// 利き手チェック
if (f.hand.value == "") {
	error_message += "利き手を選択してください。\n"; }

// 身長チェック
if (f.height.value.match(/[^0-9|-]+/)) {
error_message += "身長は数値を入力してください。\n"; }

// 体重チェック
if (f.weight.value.match(/[^0-9|-]+/)) {
error_message += "体重は数値を入力してください。\n"; }

// エラー判定
if (error_message != "") {
error_message = "入力内容に誤りがあります。\n以下の内容を確認してください。\n\n" + error_message;
alert(error_message);
return false; }
return true;
}