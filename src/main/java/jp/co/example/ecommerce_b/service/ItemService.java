package jp.co.example.ecommerce_b.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.form.SortForm;
import jp.co.example.ecommerce_b.repository.ItemRepository;

/**
 * 商品を操作するサービス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository repository;

	/**
	 * 商品を曖昧検索する.
	 * 
	 * @param name 名前
	 * @return 曖昧検索結果
	 */
	public List<Item> findByName(SortForm sortForm) {
		String searchName = sortForm.getSearchName();
		String sort = sortForm.getSort();
		List<Item> itemList = null;
		if(searchName == null || searchName.isEmpty()) {
			itemList = repository.findAll();
		} else {
			itemList = repository.findByNameAndSort(searchName, sort);
		}
		return itemList;
	}
	
	/**
	 * 値段が高い順、低い順で商品を検索する.
	 * 
	 * @return 値段が高い順、低い順の商品一覧
	 */
	public List<Item> sortItemByMoney(SortForm sortForm) {
		String sort = sortForm.getSort();
		List<Item> itemList = null;
		if ("expensive".equals(sort)) {
			itemList = repository.findByExpensive();
		} else if("cheap".equals(sort)){
			itemList = repository.findByCheap();
		} else {
			itemList = repository.findAll();
		}
		return itemList;
	}

	/**
	 * ページング用メソッド.
	 * 
	 * @param page         表示させたいページ数
	 * @param size         １ページに表示させる従業員数
	 * @param employeeList 絞り込み対象リスト
	 * @return １ページに表示されるサイズ分の従業員一覧情報
	 */
	public Page<Item> showListPaging(int page, int size, List<Item> itemList) {
		// 表示させたいページ数を-1しなければうまく動かない
		page--;
		// どの従業員から表示させるかと言うカウント値
		int startItemCount = page * size;
		// 絞り込んだ後の従業員リストが入る変数
		List<Item> list;

		if (itemList.size() < startItemCount) {
			// (ありえないが)もし表示させたい従業員カウントがサイズよりも大きい場合は空のリストを返す
			list = Collections.emptyList();
		} else {
			// 該当ページに表示させる従業員一覧を作成
			int toIndex = Math.min(startItemCount + size, itemList.size());
			list = itemList.subList(startItemCount, toIndex);
		}

		// 上記で作成した該当ページに表示させる従業員一覧をページングできる形に変換して返す
		Page<Item> itemPage = new PageImpl<Item>(list, PageRequest.of(page, size), itemList.size());
		System.out.println("itemPage : " + itemPage);
		return itemPage;
	}

	/**
	 * オートコンプリート用にJavaScriptの配列の中身を文字列で作ります.
	 * 
	 * @param itemList 商品一覧
	 * @return オートコンプリート用JavaScriptの配列の文字列
	 */
	public StringBuilder getItemListForAutocomplete(List<Item> itemList) {
		StringBuilder itemListForAutocomplete = new StringBuilder();
		for (int i = 0; i < itemList.size(); i++) {
			if (i != 0) {
				itemListForAutocomplete.append(",");
			}
			Item item = itemList.get(i);
			itemListForAutocomplete.append("\"");
			itemListForAutocomplete.append(item.getName());
			itemListForAutocomplete.append("\"");
		}
		return itemListForAutocomplete;
	}

	/**
	 * オートコンプリートで使用.
	 * 
	 * @return 商品一覧
	 */
	public List<Item> itemList() {
		return repository.findAll();

	}
}
