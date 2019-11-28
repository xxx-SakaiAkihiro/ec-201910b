package jp.co.example.ecommerce_b.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.CreditCard;
import jp.co.example.ecommerce_b.domain.CreditCardData;
import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.form.OrderForm;
import jp.co.example.ecommerce_b.repository.OrderRepository;
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
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	/**
	 * 商品を購入する.
	 * 
	 * @param orderForm 注文フォーム
	 * @return 注文完了画面
	 */
	@RequestMapping("")
	public String purchase(@Validated OrderForm orderForm,BindingResult result,@AuthenticationPrincipal LoginUser loginUser) {
		List<Order> orderList = orderRepository.findByUserIdAndStatus(loginUser.getUser().getId(), 0);
		Order order = orderList.get(0);
		System.out.println(orderForm.getOrderDate());
		order.setTotalPrice(Integer.parseInt(orderForm.getTotalPrice()));
		order.setDestinationName(orderForm.getDestinationName());
		order.setDestinationEmail(orderForm.getDestinationEmail());
		order.setDestinationZipcode(orderForm.getDestinationZipcode());
		order.setDestinationAddress(orderForm.getDestinationAddress());
		order.setDestinationTel(orderForm.getDestinationTel());
		order.setPaymentMethod((Integer.parseInt(orderForm.getPaymentMethod())));
		System.out.println(orderForm.getDeliveryDate());
		System.out.println(orderForm.getDeliveryTime());
		
		String deliveryDateTime = orderForm.getDeliveryDate() +  " " + orderForm.getDeliveryTime() + ":00:00";
		System.out.println(deliveryDateTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date parsedDate = null;
		try {
			parsedDate = format.parse(deliveryDateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		order.setDeliveryTime(timestamp);
		if("1".equals(orderForm.getPaymentMethod())) {
			order.setStatus(1);
		}
		if("2".equals(orderForm.getPaymentMethod())) {
			order.setStatus(2);
			/** orderForm で受け取ったリクエストパラメータをcreditCardにセットする */
			CreditCard creditCard = new CreditCard(); 
			creditCard.setCard_number(orderForm.getCard_number());
			creditCard.setCard_exp_month(orderForm.getCard_exp_month());
			creditCard.setCard_exp_year(orderForm.getCard_exp_year());
			creditCard.setCard_name(orderForm.getCard_name());
			creditCard.setCard_cvv(orderForm.getCard_cvv());
			
			CreditCardData creditCardData=purchaseService.creditCardCall(creditCard);
			
			if ("error".equals(creditCardData.getMessage())) {
				result.rejectValue("card_number", null, "クレジットカード情報が不正です");
				return "foward:/purchase";
			}
		}
		System.out.println(order);
		purchaseService.purchase(order);
		return "order_finished";
	}
	
	@RequestMapping("/mail")
	public void sendMail(@AuthenticationPrincipal LoginUser loginUser) {
		System.out.println(loginUser);
		purchaseService.sendMail(loginUser);
	}
	
	
		
	
	
	
}
