package jp.co.example.ecommerce_b.controller;

import java.util.ArrayList;
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
	 * @param name  名前
	 * @param model モデル
	 * @return 商品画面を表示
	 */
	@RequestMapping("")
	public String showItemListFindByName(String name, Integer pageNumber, Model model) {
		
		Integer count = service.count();
		System.out.println("count : " + count);
		int maxPageNumber = 0;
		List<Integer> pageNumbers = new ArrayList<Integer>();
		if(count % 6 != 0) {
			maxPageNumber = count / 6 + 1;
		} else {
			maxPageNumber = count / 6;
		}
		for (int i = 1; i <= maxPageNumber; i++) {
			pageNumbers.add(i);
		}
		model.addAttribute("pageNumbers", pageNumbers);

		List<List<Item>> itemListList = service.showItemListFindByName(name, pageNumber);
		if (itemListList.isEmpty()) {
			model.addAttribute("message", "該当する商品はありません");
			// 商品が１つもなければ全件検索を行う
			itemListList = service.showItemListFindByName("", 1);
		}
		model.addAttribute("itemListList", itemListList);
		return "item_list";
	}
}
