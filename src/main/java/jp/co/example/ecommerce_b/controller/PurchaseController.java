package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.CreditCard;
import jp.co.example.ecommerce_b.domain.CreditCardData;
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
	public String purchase(@Validated OrderForm orderForm,BindingResult result) {
		Order order = new Order();
		order.setDestinationName(orderForm.getDestinationName());
		order.setDestinationEmail(orderForm.getDestinationEmail());
		order.setDestinationZipcode(orderForm.getDestinationZipcode());
		order.setDestinationAddress(orderForm.getDestinationAddress());
		order.setDestinationTel(orderForm.getDestinationTel());
		order.setDeliveryTime(orderForm.getDeliveryTime());
//		/order.setPaymentMethod((Integer.parseInt(orderForm.getPaymentMethod())));
//		if("1".equals(orderForm.getPaymentMethod())) {
//			order.setStatus(1);
//		}
//		if("2".equals(orderForm.getPaymentMethod())) {
//			order.setStatus(2);
//		}
		///purchaseService.purchase(order);
		
		
		/** orderForm で受け取ったリクエストパラメータをcreditCardにセットする 
		CreditCard creditCard = new CreditCard(); 
		creditCard.setCard_number(orderForm.getCard_number());
		creditCard.setCard_exp_month(orderForm.getCard_exp_month());
		creditCard.setCard_exp_year(orderForm.getCard_exp_year());
		creditCard.setCard_name(orderForm.getCard_name());
		creditCard.setCard_cvv(orderForm.getCard_cvv());
		
		CreditCardData creditCardData=purchaseService.creditCardCall(creditCard);
		
		if (creditCardData.getMessage().equals("error")) {
			result.rejectValue("card_number", null, "クレジットカード情報が不正です");
			
			return "foward:/purchase";
			
		}
		
		↑競合しても消さないで　*/
		
		
		
		
		
		return "order_finished";
	}
	
	@RequestMapping("/mail")
	public void sendMail() {
		purchaseService.sendMail();
	}
	
	
		
	
	
	
}
