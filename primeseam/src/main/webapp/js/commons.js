
 function handleDialogSubmit(xhr, status, args, dlg) {
	        if (args.validationFailed) {
	            alert('invalid');
	        	dlg.show();
	        } else {
	        	alert('valid');
	        	dlg.hide();
	        }
	    }


function handleDialogSubmit(xhr, status, args) {
	if (args != null) {
		if (args.validationFailed) {
			editProductDialog.show();
		} else {
			editProductDialog.hide();
		}
	}
}

function processObjectsChange(suggestionBox) {
    var id = suggestionBox.oldValue;
    this.location.href = location + "?id=" + id;
}


function processObjectsChangeLoc(suggestionBox, loc) {
	//alert(loc);
    var id = suggestionBox.oldValue;
    this.location.href = loc + "?id=" + id;
}


function processObjectsChangeLocWithParamName(suggestionBox, loc, name) {
	//alert(loc);
    var id = suggestionBox.oldValue;
    this.location.href = loc + "?" + name + "=" + id;
}

function processSearch( loc, id, name) {
	alert(loc + " " + id + " " +name);
	
    var id = suggestionBox.oldValue;
    this.location.href = loc + "?" + name + "=" + id;
}


