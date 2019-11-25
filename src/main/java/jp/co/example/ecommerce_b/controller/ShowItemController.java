package jp.co.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.service.ItemService;

/**
 * 商品一覧を表示、検索するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@Controller
@RequestMapping("/showItem")
public class ShowItemController {
	
	@Autowired
	private ItemService service;
	
	/**
	 * 商品一覧を表示する.
	 * 
	 * @param model モデル
	 * @return　商品一覧画面
	 */
	@RequestMapping("")
	public String showAllItems(Model model) {
		List<Item> itemList = service.showAllItems();
		System.out.println(itemList);
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
	
	/**
	 * 曖昧検索をする.
	 * 
	 * @param name 名前
	 * @param model　モデル
	 * @return　商品画面を表示
	 */
	@RequestMapping("/showItemList")
	public String showItemListFindByName(String name, Model model) {
		System.out.println(name);
		List<Item> itemList = service.showItemListFindByName(name);
		if(itemList.isEmpty()) {
			model.addAttribute("message", "該当する商品はありません");
			itemList = service.showAllItems();
		}
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
}
