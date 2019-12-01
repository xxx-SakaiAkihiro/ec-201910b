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
		console.log("サイズMorL"+size);
		var topping_count = $("#topping input:checkbox:checked").length;
		console.log("トッピングの個数"+topping_count);
		var num = $("#num option:selected").val();
		console.log("数量"+num);
		
		if (size == "M") {
			var size_price =parseInt($('#m_price').val()) ;
			console.log("Mの料金"+size_price);
			var topping_price = 200 * topping_count;
		} else {
			var size_price = parseInt($('#l_price').val()) ;
			console.log("Lの料金"+size_price);
			var topping_price = 300 * topping_count;
		}
		var price = (size_price + topping_price) * num;
		console.log(num);
		console.log(price);
		console.log(size_price);
		$("#totalprice").text(price.toLocaleString());
	};
	
});