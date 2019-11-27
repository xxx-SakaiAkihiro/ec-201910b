package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.service.DeleteItemService;

/**
 * カートの商品を削除するコントローラ.
 * 
 * @author riho.ikeda
 *
 */
@Controller
@RequestMapping("/DeleteItem")
public class DeleteItemController {

	@Autowired
	private DeleteItemService deleteItemService;

	/**
	 * カートの商品を削除する.
	 * 
	 * @param orderItemId ID
	 * @return カートの中身を表示するコントローラにリダイレクト.
	 */
	@RequestMapping("")
	public String deleteItem(Integer orderItemId) {
		deleteItemService.deleteItem(orderItemId);
		return "redirect:/ShowOrderItem";
	}

}
