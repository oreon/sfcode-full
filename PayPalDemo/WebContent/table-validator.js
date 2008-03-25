//global variables
var qv1;
var qv2;
var qv3;
var qv4;
var val1;
var val2;
var val3;
var val4;

//validate an integer
function validateInteger(validatorId,valueId,minValue,maxValue){
var vi=validatorId;
var valElement=document.getElementById(vi);
var value=Number(valueId.value);

if(isNaN(value)||(value!=Math.round(value))||(minValue>value)||(value>maxValue)){
document.getElementById(vi).innerHTML="Please enter a number between "+minValue+" and "+maxValue+"!";
return false;
}
valElement.innerHTML="";
return true;
}
function validateQuantity(validatorId,quantityId){
	return validateInteger(validatorId,quantityId,0,10);
}

function getValues(){
val1=document.getElementById("quantityId1");
val2=document.getElementById("quantityId2");
val3=document.getElementById("quantityId3");
val4=document.getElementById("quantityId4");
}

function validateFields(){
getValidators();
getValues();
var quantityValid1=validateQuantity(qv1,val1);
var quantityValid2=validateQuantity(qv2,val2);
var quantityValid3=validateQuantity(qv3,val3);
var quantityValid4=validateQuantity(qv4,val4);
	return quantityValid1&&quantityValid2&&quantityValid3&&quantityValid4;}

function submitOrder(){
	var valid=validateFields();
	if(!valid){
		window.alert("OOps-Number must be 0-10\n"+"Please correct errors!" )
	}
	return valid;}
	
function getValidators(){
	qv1=document.getElementById("quantityValidator1");
	qv2=document.getElementById("quantityValidator2");
	qv3=document.getElementById("quantityValidator3");
	qv4=document.getElementById("quantityValidator4");}	
	
function resetValidators(){
	document.getElementById("quantityValidator1").innerHTML="";
	document.getElementById("quantityValidator2").innerHTML="";
	document.getElementById("quantityValidator3").innerHTML="";
	document.getElementById("quantityValidator4").innerHTML="";}