package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.form.OrderForm;
import jp.co.example.ecommerce_b.service.PurchaseService;

/**
 * 商品を購入するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	
	
	/**
	 * 商品を購入する.
	 * 
	 * @param orderForm 注文フォーム
	 * @return 注文完了画面
	 */
	@RequestMapping("")
	public String purchase(OrderForm orderForm) {
		Order order = new Order();
		order.setDestinationName(orderForm.getDestinationName());
		order.setDestinationEmail(orderForm.getDestinationEmail());
		order.setDestinationZipcode(orderForm.getDestinationZipcode());
		order.setDestinationAddress(orderForm.getDestinationAddress());
		order.setDestinationTel(orderForm.getDestinationTel());
		order.setDeliveryTime(orderForm.getDeliveryTime());
		order.setPaymentMethod((Integer.parseInt(orderForm.getPaymentMethod())));
		if("1".equals(orderForm.getPaymentMethod())) {
			order.setStatus(1);
		}
		if("2".equals(orderForm.getPaymentMethod())) {
			order.setStatus(2);
		}
		purchaseService.purchase(order);
		return "order_finished";
	}
	
	@RequestMapping("/mail")
	public void sendMail(@AuthenticationPrincipal LoginUser loginUser) {
		System.out.println(loginUser);
		purchaseService.sendMail(loginUser);
	}
}
