//validate an integer
function validateInteger(validattorId,valueId,minValue,maxValue){
var valElement=document.getElementById(valueId);
var value=Number(valElement.value);

if(isNaN(value)||(value!=Math.round(value))||(minValue>value)||(value>maxValue)){
valElement.innerHTML="Please enter a number between "+minValue+" and "+maxValue+"!";
return false;
}
valElement.innerHTML="";
return true;
}
function validateQuantity(validatorId,quantityId){
	return validateInteger(validatorId,quantityId,0,10);
}

function validateFields(){
var quantityValid1=validateQuantity("quantityValidator","quantityId1");
var quantityValid2=validateQuantity("quantityValidator","quantityId2");
var quantityValid3=validateQuantity("quantityValidator","quantityId3");
var quantityValid4=validateQuantity("quantityValidator","quantityId3");
	return quantityValid1&&quantityValid2&&quantityValid3&&quantityValid4;}

function submitOrder(){
	var valid=validateFields();
	if(!valid){
		window.alert("OOps-Number must be 0-10\n"+"Please correct errors!" )
	}
	return valid;}
	
function resetValidators(){
	document.getElementById(quantityValidator).innerHTML="";}