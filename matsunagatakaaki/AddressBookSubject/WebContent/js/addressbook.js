/**
 *
 */

//f5を無効化
window.document.onkeydown = function(){
	if(event.keyCode == 116){
	    event.keyCode = null;
	    return false;
	}
}

/*
 * 更新内容入力ページで、各項目の入力内容を空にする。
 *   <input>タグ内に正規表現を用いているため、不適切な文字を入力したまま「一覧表示」ボタンを押下すると、
 *   登録情報一覧ページに遷移せずに、注意書きが表示される。
 *   そのため、遷移前に入力欄を空にする必要がある。
 */
function itemsReset(){
    var ids = ["name","address","tel"];
    for(var i = 0 ; i<ids.length ; i++){
        if(getValue(ids[i]) == "") continue; //未入力なら次へ
        setValue(ids[i],"");
    }
}

//指定したidの値を取得
function getValue(id){
    return document.getElementById(id).value;
}

//指定したidへ値をセット
function setValue(id,value){
    document.getElementById(id).value = value;
}