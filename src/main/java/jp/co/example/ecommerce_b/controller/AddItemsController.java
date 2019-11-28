package jp.co.example.ecommerce_b.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
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
	public AddItemService service;
	
	/**
	 * 商品を追加する.
	 * 
	 * @param orderItemForm 商品注文フォーム
	 * @return 商品確認画面
	 */
	@RequestMapping("/addItem")
	public String addItem(OrderItemForm orderItemForm,@AuthenticationPrincipal LoginUser loginUser) {
		service.addItem(orderItemForm, loginUser);
		return "redirect:/ShowOrderItem";

	}
}
