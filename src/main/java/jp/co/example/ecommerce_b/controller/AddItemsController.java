package jp.co.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.OrderTopping;
import jp.co.example.ecommerce_b.form.OrderItemForm;
import jp.co.example.ecommerce_b.service.AddItemService;

/**
 * 商品を追加するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@Controller
@RequestMapping("/add")
public class AddItemsController {
	
	@Autowired
	private HttpSession session;

	@Autowired
	public AddItemService service;
	
	/**
	 * 商品を追加する.
	 * 
	 * @param orderItemForm 商品注文フォーム
	 * @return 商品詳細画面
	 */
//	@RequestMapping("/addItem")
//	public String addItem(OrderItemForm orderItemForm) {
//		Order order = new Order();
//		order.setUserId(0);
//		order.setStatus(0);
//		service.addItem(order, orderItem, orderTopping);
//		
//		OrderItem orderItem = new OrderItem();
//		orderItem.setItemId(Integer.parseInt(orderItemForm.getItemId()));
//		orderItem.setOrderId(order.getId());
//		orderItem.setQuantity(Integer.parseInt(orderItemForm.getQuantity()));
//		orderItem.setSize(orderItemForm.getSize());
//		service.addItem(order, orderItem, orderTopping);
//		
//		OrderTopping orderTopping = new OrderTopping();
//		orderTopping.setOrderItemId(orderItem.getId());
//		for( Integer toppingId : orderItemForm.getOrderToppingList() ) {
//			orderTopping.setToppingId(toppingId);
//			service.addItem(order, orderItem, orderTopping);
//		}
//		session.setAttribute("orderItem", orderItem);
//		service.addItem(order, orderItem, orderTopping);
//		return "cart_list";
//	}
}
