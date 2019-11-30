package jp.co.example.ecommerce_b.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("")
public class ShowItemController {

	@Autowired
	private ItemService service;

	@Autowired
	private CountInCartService countInCartService;

	// 1ページに表示する商品数は6品
	private static final int VIEW_SIZE = 6;

	@Autowired
	private HttpSession session;

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
//	public String NeedPage(SortForm sortForm,Model model) {
//		List<Integer> pageNumbers = service.NeedPage(sortForm);
//		model.addAttribute("pageNumbers", pageNumbers);
//		return "item_list";
//	}

	/**
	 * 曖昧検索、全件検索、値段が高い順、低い順で商品を検索する.
	 * 
	 * @param name  名前
	 * @param model モデル
	 * @return 商品画面を表示
	 */
	@RequestMapping("/")
	public String showItemListFindByName(SortForm sortForm, Integer page, Model model,
			@AuthenticationPrincipal LoginUser loginUser) {
		String searchName = sortForm.getSearchName();

		// ページング機能追加
		if (page == null) {
			// ページ数の指定が無い場合は1ページ目を表示させる
			page = 1;
		}
		
		List<Item> itemList = service.findByNameAndSort(sortForm);
		if (itemList.isEmpty()) {
			itemList = service.itemList();
			model.addAttribute("message", "該当する商品はありません");
			System.out.println("曖昧検索 : " + itemList);
		} else {
			itemList = service.findByNameAndSort(sortForm);
			session.setAttribute("searchName", searchName);
			System.out.println("全件検索 : " + itemList);
		}
		
		// 表示させたいページ数、ページサイズ、従業員リストを渡し１ページに表示させる従業員リストを絞り込み
		Page<Item> itemPage = service.showListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);

		// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
		List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		model.addAttribute("pageNumbers", pageNumbers);

//		List<Item> itemList = service.findByNameAndSort(sortForm);
//		model.addAttribute("itemList", itemList);
//		if (itemList.isEmpty()) {
//			// 商品が１つもなければ全件検索を行う
//			model.addAttribute("message", "該当する商品はありません");
//			sortForm.setSearchName("");
//			itemList = service.findByNameAndSort(sortForm);
//			model.addAttribute("itemList", itemList);
//		}

//		// 曖昧検索,全件検索
//		NeedPage(sortForm, model);
//		//初期ページでは全件検索される
//		List<List<Item>> itemListList = service.findByNameAndSort(sortForm);
//		System.out.println("全件検索Con : " + itemListList);
//
//		session.setAttribute("searchName", sortForm.getSearchName());
//		model.addAttribute("itemListList", itemListList);
//		
//		if (itemListList.isEmpty()) {
//			// 商品が１つもなければ全件検索を行う
//			model.addAttribute("message", "該当する商品はありません");
//			sortForm.setSearchName("");
//			NeedPage(sortForm, model);
//			itemListList = service.findByNameAndSort(sortForm);
//			model.addAttribute("itemListList", itemListList);
//		} 

//		
//			// 値段が高い順、低い順で商品を検索
//			session.setAttribute("sort", sortForm.getSort());
//			NeedPage(model);
//			itemListList = service.findByNameAndSort(sortForm);
//			model.addAttribute("itemListList", itemListList);

		// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
		StringBuilder ItemListForAutocomplete = service.getItemListForAutocomplete(service.itemList());
		model.addAttribute("ItemListForAutocomplete", ItemListForAutocomplete);
		// 「ショッピングカート」リンクのアイコンバッジの件数を取得
		Integer countInCart = countInCartService.countInCart(loginUser);
		model.addAttribute("countInCart", countInCart);
		return "item_list";
	}
	/**
	 * ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
	 * 
	 * @param model        モデル
	 * @param employeePage ページング情報
	 */
	private List<Integer> calcPageNumbers(Model model, Page<Item> itemPage) {
		int totalPages = itemPage.getTotalPages();
		List<Integer> pageNumbers = null;
		if (totalPages > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNumbers.add(i);
			}
		}
		return pageNumbers;
	}

	

}
