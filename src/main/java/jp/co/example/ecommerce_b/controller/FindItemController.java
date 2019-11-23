package jp.co.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.service.ItemService;

@Controller
@RequestMapping("/find")
public class FindItemController {
	
	@Autowired
	private ItemService service;

	/**
	 * 曖昧検索をする.
	 * 
	 * @param name 名前
	 * @param model　モデル
	 * @return　商品画面を表示
	 */
	@RequestMapping("/findItem")
	public String findItem(String name, Model model) {
		List<Item> itemList = service.findByName(name);
		if(itemList.isEmpty()) {
			model.addAttribute("message", "該当する商品はありません");
			itemList = service.findAll();
		}
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
}
