/**
 * 
 */


	$("#card_number").hide();
	$("#card_exp").hide();
	$("#card_name").hide();
	$("#card_cvv").hide();
	
	
	$("#bonus").on("change",function(){
		if ($("#bonus").val()=="1") {
			$("#java").hide();
			$("#javascript").hide();
		}else if ($("#bonus").val()=="2") {
			$("#java").show();
			$("#javascript").hide();
		}else if ($("#bonus").val()=="3") {
			$("#java").hide();
			$("#javascript").show();
		}
		
	});