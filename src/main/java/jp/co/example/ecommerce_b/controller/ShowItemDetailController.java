package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.service.ShowItemDetailService;

/**
 * @author riho.ikeda
 *
 *商品詳細を表示するコントローラ.
 */
@Controller
@RequestMapping("/ShowItemDetail")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService showitemDetailService;

	/**
	 * 商品詳細を表示する.
	 * 
	 * @param id ID
	 * @return 商品1件の検索結果
	 */
	@RequestMapping("")
	public String showItemDetail(Integer id, Model model) {
		Item item = showitemDetailService.showItemDetail(id);
		model.addAttribute("item",item);
		return "item_detail";

	}

}
