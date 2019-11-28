package jp.co.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.form.SortForm;
import jp.co.example.ecommerce_b.service.CountInCartService;
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

	@Autowired
	private CountInCartService countInCartService;
	
	@ModelAttribute
	public SortForm setUpForm() {
		return new SortForm();
	}
	
	/**
	 * 曖昧検索と並び替え順で使うメソッド.
	 * 
	 * 商品の件数に対して何ページ必要か
	 * 
	 * @param model モデル
	 */
	public String NeedPage(Model model) {
		List<Integer> pageNumbers = service.NeedPage();
		model.addAttribute("pageNumbers", pageNumbers);
		return "item_list";
	}
	
	/**
	 * 曖昧検索をする.
	 * 
	 * @param name  名前
	 * @param model モデル
	 * @return 商品画面を表示
	 */
	@RequestMapping("")
	public String showItemListFindByName(SortForm sortForm, Model model,@AuthenticationPrincipal LoginUser loginUser) {
		NeedPage(model);
		model.addAttribute("name", sortForm.getSearchName());
		List<List<Item>> itemListList = service.showItemListFindByName(sortForm);
		if (itemListList.isEmpty()) {
			model.addAttribute("message", "該当する商品はありません");
			// 商品が１つもなければ全件検索を行う
			itemListList = service.showItemListFindByName(sortForm);
		}
		model.addAttribute("itemListList", itemListList);

		// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
		StringBuilder ItemListForAutocomplete = service.getItemListForAutocomplete(service.itemList());
		model.addAttribute("ItemListForAutocomplete", ItemListForAutocomplete);
		// 「ショッピングカート」リンクのアイコンバッジの件数を取得
		Integer countInCart = countInCartService.countInCart(loginUser);
		model.addAttribute("countInCart",countInCart);
		return "item_list";
	}

	/**
	 * 値段が高い順、低い順で商品を検索する.
	 * 
	 * @param model モデル
	 * @return 値段が高い順、低い順の商品一覧
	 */
	@RequestMapping("/sortItems")
	public String sortByMoneyItem(SortForm sortForm, Model model) {
		NeedPage(model);
		Integer startNumber = service.SearchStartNumber(sortForm);
		List<List<Item>> itemListList = service.sortItemByMoney(sortForm, startNumber);
		model.addAttribute("itemListList", itemListList);
		return "item_list";
	}
}
