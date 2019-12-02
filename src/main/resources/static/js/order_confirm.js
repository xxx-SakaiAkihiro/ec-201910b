/**
 * 
 */
$( function() {

	$("#card_number").hide();
	$("#card_exp").hide();
	$("#card_name").hide();
	$("#card_cvv").hide();
	
	
	$(".paymentMethod").on("change",function(){
		console.log($("input[name='paymentMethod']:checked").val());
		if ($("input[name='paymentMethod']:checked").val()=="1") {
			$("#card_number").hide();
			$("#card_exp").hide();
			$("#card_name").hide();
			$("#card_cvv").hide();
		}else if ($("input[name='paymentMethod']:checked").val()=="2") {
			$("#card_number").show();
			$("#card_exp").show();
			$("#card_name").show();
			$("#card_cvv").show();
		}
	});
	
	var today = new Date();
    today.setDate(today.getDate());
    var yyyy = today.getFullYear();
    var mm = ("0"+(today.getMonth()+1)).slice(-2);
    var dd = ("0"+(today.getDate()+1)).slice(-2);
    document.getElementById("deliveryTime").setAttribute("min",yyyy+'-'+mm+'-'+dd);
	console.log(document.getElementById("deliveryTime").value)
	
	
	
});
	
