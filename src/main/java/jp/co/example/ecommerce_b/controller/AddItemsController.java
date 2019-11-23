package jp.co.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.OrderTopping;
import jp.co.example.ecommerce_b.form.OrderItemForm;
import jp.co.example.ecommerce_b.form.OrderToppingForm;
import jp.co.example.ecommerce_b.service.AddItemsService;

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
	public AddItemsService service;
	
	/**
	 * 商品を追加する.
	 * 
	 * @param orderItemForm 商品注文フォーム
	 * @return 商品詳細画面
	 */
	@RequestMapping("/addItems")
	public String addItems(OrderItemForm orderItemForm) {
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(orderItemForm, orderItem);
		orderItem.setId((Integer.parseInt(orderItemForm.getId())));
		orderItem.setItemId((Integer.parseInt(orderItemForm.getItemId())));
		orderItem.setOrderId((Integer.parseInt(orderItemForm.getOrderId())));
		orderItem.setQuantity((Integer.parseInt(orderItemForm.getQuantity())));
		session.setAttribute("orderItem", orderItem);
		service.insertOrderItem(orderItem);
		return "item_detail";
	}
	/**
	 * トッピングを追加する.
	 * 
	 * @param orderToppingForm トッピング注文フォーム
	 * @return 商品詳細画面
	 */
	@RequestMapping("/addToppings")
	public String addToppings(OrderToppingForm orderToppingForm) {
		OrderTopping orderTopping = new OrderTopping();
		BeanUtils.copyProperties(orderToppingForm, orderTopping);
		orderTopping.setId((Integer.parseInt(orderToppingForm.getId())));
		orderTopping.setToppingId((Integer.parseInt(orderToppingForm.getToppingId())));
		orderTopping.setOrderItemId((Integer.parseInt(orderToppingForm.getOrderItemId())));
		service.insertOrderTopping(orderTopping);
		return "item_detail";
	}
}
