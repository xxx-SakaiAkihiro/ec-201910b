package jp.co.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.service.PurchaseService;

/**
 * 商品を購入するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@Controller
@RequestMapping("/purchaseController")
public class PurchaseController {

	@Autowired
	private PurchaseService service;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 商品を購入する.
	 * 
	 * @param orderForm 注文フォーム
	 * @return 注文完了画面
	 */
	@RequestMapping("/purchase")
	public String purchase(OrdereForm orderForm) {
		Order order = new Order();
		BeanUtils.copyProperties(orderForm, order);
		order.setId((Integer.parseInt(orderForm.getId())));
		order.setUserId((Integer.parseInt(orderForm.getUserId())));
		order.setStatus((Integer.parseInt(orderForm.getStatus())));
		order.setTotalPrice((Integer.parseInt(orderForm.getTotalPrice())));
		order.setOrderDate((Integer.parseInt(orderForm.getOrderDate())));
		order.setDestinationName((Integer.parseInt(orderForm.getDestinationName())));
		order.setDestinationEmail((Integer.parseInt(orderForm.getDestinationEmail())));
		order.setDestinationZipcode((Integer.parseInt(orderForm.getDestinationZipcode())));
		order.setDestinationAddress((Integer.parseInt(orderForm.getDestinationAddress())));
		order.setDestinationTel((Integer.parseInt(orderForm.getDestinationTel())));
		order.setDeliveryTime((Integer.parseInt(orderForm.getDeliveryTime())));
		order.setPaymentMethod((Integer.parseInt(orderForm.getPaymentMethod())));
		session.setAttribute("order", order);
		service.insertOrder(order);
		return "order_finished";
	}
}
