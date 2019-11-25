package jp.co.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String purchase(OrderForm orderForm) {
		Order order = new Order();
		BeanUtils.copyProperties(orderForm, order);
		order.setId((Integer.parseInt(orderForm.getId())));
		order.setUserId((Integer.parseInt(orderForm.getUserId())));
		order.setStatus((Integer.parseInt(orderForm.getStatus())));
		order.setTotalPrice((Integer.parseInt(orderForm.getTotalPrice())));
		order.setPaymentMethod((Integer.parseInt(orderForm.getPaymentMethod())));
		session.setAttribute("order", order);
		service.insertOrder(order);
		return "order_finished";
	}
}
