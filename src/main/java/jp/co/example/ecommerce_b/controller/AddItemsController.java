package jp.co.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.form.OrderItemForm;
import jp.co.example.ecommerce_b.service.AddItemsService;

@Controller
@RequestMapping("/add")
public class AddItemsController {
	
	@Autowired
	private HttpSession session;

	@Autowired
	public AddItemsService service;
	
	@RequestMapping("/addItems")
	public String addItems(OrderItemForm orderItemForm) {
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(orderItemForm, orderItem);
		orderItem.setId((Integer.parseInt(orderItemForm.getId())));
		orderItem.setItemId((Integer.parseInt(orderItemForm.getItemId())));
		orderItem.setOrderId((Integer.parseInt(orderItemForm.getOrderId())));
		orderItem.setQuantity((Integer.parseInt(orderItemForm.getQuantity())));
		session.setAttribute("orderItem", orderItem);
		return "item_detail";
	}
	@RequestMapping("/")
	public String addToppings(OrderToppingForm orderToppingForm) {
		
		return "item_detail";
	}
}
