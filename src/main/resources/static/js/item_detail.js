/**
 * 
 */

$(function() {
	calc_price();
	$(".size").on("change",function() {
		calc_price();
	});

	$(".checkbox").on("change",function() {
		calc_price();
	});

	$("#num").on("change",function() {
		calc_price();
	});
	
	function calc_price() {
		var size = $(".size:checked").val();
		var topping_count = $("#topping input:checkbox:checked").length;
		var num = $("#num option:selected").val();
		if (size == "m") {
			var size_price =$('m').text() ;
			var topping_price = 200 * topping_count;
		} else {
			var size_price = $('l').text() ;
			var topping_price = 300 * topping_count;
		}
		var price = (size_price + topping_price) * num;
		$("#totalprice").text(price.toLocaleString());
	}
	;
});