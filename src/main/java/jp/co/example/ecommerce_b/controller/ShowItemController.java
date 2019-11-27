package jp.co.example.ecommerce_b.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	private static final int VIEW_SIZE = 9;
	
	/**
	 * 曖昧検索をする.
	 * 
	 * @param name 名前
	 * @param model　モデル
	 * @return　商品画面を表示
	 */
	@RequestMapping("")
	public String showItemListFindByName(String name, Model model,Integer page) {
		//ページング機能
		if (page == null) {
			page = 1;//pageの引数がなければ1ページ目を表示
		}
		List<List<Item>> itemListList = service.showItemListFindByName(name);
		if(itemListList.isEmpty()) {
			model.addAttribute("message", "該当する商品はありません");
			// 商品が１つもなければ全件検索を行う
			itemListList = service.showItemListFindByName("");
		}
		model.addAttribute("itemListList", itemListList);
		//表示させたいページ数、ページサイズ、アイテムリストを渡し1ページに表示させるアイテムリストを絞り込み
//		Page<Item> itemPage = service.showListPaging(page,VIEW_SIZE,itemListList);
//		model.addAttribute("itemPage",itemPage);
		
//		//ページングのリンクに使うページ数をスコープに格納
//		List<Integer> pageNumbers = calcPageNumbers(model,itemPage);
//		model.addAttribute(("pageNumbers",pageNumbers);
		
		return "item_list";
	}
	
//	public List<Integer> calcPageNumbers(Model model,Page<Item> itemPage){
//		int totalPages = itemPage.getTotalPages();
//		List<Integer> pageNumbers = null;
//		if(totalPages > 0) {
//			pageNumbers = new ArrayList<Integer>();
//			for(int i = 1; i <= totalPages; i++) {
//				pageNumbers.add(i);
//			}
//		}
//		return pageNumbers;
//	}
}
