package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jp.co.example.ecommerce_b.service.ItemDetailService;

/**
 * @author riho.ikeda
 *
 *商品詳細を表示するコントローラ.
 */
@Controller
public class ShowItemDetailController {

	@Autowired
	private ItemDetailService itemDetailService;

	/**
	 * 商品詳細を表示する.
	 * 
	 * @param id ID
	 * @return 商品1件の検索結果
	 */
	public String showItemDetail(String id, Model model) {
		itemDetailService.load(Integer.parseInt(id));
		return "item_detail";

	}

}
