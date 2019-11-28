/**
 * 
 */
$(function(){
	$("#destinationAddress").on("click",function(){	
		AjaxZip3.zip2addr('destinationZipcode','','destinationAddress','destinationAddress');
	});
});