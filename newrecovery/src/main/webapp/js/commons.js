

function processObjectsChange(suggestionBox) {
    var id = suggestionBox.oldValue;
    this.location.href = location + "?id=" + id;
}


function processObjectsChangeLoc(suggestionBox, loc) {
	//alert(loc);
    var id = suggestionBox.oldValue;
    this.location.href = loc + "?id=" + id;
}



