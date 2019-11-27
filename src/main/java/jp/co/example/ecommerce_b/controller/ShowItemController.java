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
	 * 曖昧検索をする.
	 * 
	 * @param name 名前
	 * @param model　モデル
	 * @return　商品画面を表示
	 */
	@RequestMapping("")
	public String showItemListFindByName(String name, Model model) {
		List<List<Item>> itemListList = service.showItemListFindByName(name);
		if(itemListList.isEmpty()) {
			model.addAttribute("message", "該当する商品はありません");
			// 商品が１つもなければ全件検索を行う
			itemListList = service.showItemListFindByName("");
		}
		model.addAttribute("itemListList", itemListList);
		
		// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
		StringBuilder ItemListForAutocomplete = service.getItemListForAutocomplete(service.itemList());
		model.addAttribute("ItemListForAutocomplete", ItemListForAutocomplete);
				
				
		return "item_list";
	}
	
	
}
