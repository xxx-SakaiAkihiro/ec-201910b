package jp.co.example.ecommerce_b.service;

import java.util.ArrayList;
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
	 * 商品数に対し必要なページ数のリストを検索.
	 * 
	 * @param sortForm ソートフォーム
	 * @return 商品数に対し必要なページ数のリスト
	 */
//	public List<Integer> NeedPage(SortForm sortForm){
//		String searchName = sortForm.getSearchName();
//		Integer count = repository.orderedItemcount(searchName);
//		int maxPageNumber = 0;
//		List<Integer> pageNumbers = new ArrayList<Integer>();
//		if (count % 6 != 0) {
//			maxPageNumber = count / 6 + 1;
//		} else {
//			maxPageNumber = count / 6;
//		}
//		for (int i = 1; i <= maxPageNumber; i++) {
//			pageNumbers.add(i);
//		}
//		return pageNumbers;
//	}

	/**
	 * 商品の表示開始番号を固定する.
	 * 
	 * @param sortForm ソートフォーム
	 * @return 商品の表示開始番号
	 */
//		public Integer SearchStartNumber(SortForm sortForm) {
//		Integer startNumber = null;
//		Integer pageNumber = sortForm.getPageNumber();
//		if (pageNumber == null || pageNumber == 1) {
//			startNumber = 0;
//		} else {
//			startNumber = (pageNumber - 1) * 6;
//		}
//		return startNumber;
//	}

	/**
	 * 商品を曖昧検索する
	 * 
	 * @param sortForm
	 * @return
	 */
//	public List<Item> findByNameAndSort(SortForm sortForm) {
//		return repository.findByNameAndSort(sortForm.getSearchName(), sortForm.getSort());
//	}
	
	/**
	 * 商品全件表示
	 * 
	 * @return 商品全件
	 */
//	public List<Item> findAll(){
//		return repository.findAll();
//	}
	
	
	/**
	 * ページング用メソッド.
	 * @param page 表示させたいページ数
	 * @param size １ページに表示させる従業員数
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
	    Page<Item> itemPage
	      = new PageImpl<Item>(list, PageRequest.of(page, size), itemList.size());
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
	
	

	/**
	 * 3つ区切りで商品を表示.
	 * 
	 * @param itemList アイテム商品一覧
	 * @return 3つ区切りで商品表示
	 */
//		private List<List<Item>> devideItemsBy3(List<Item> itemList) {
//			List<List<Item>> itemListList = new ArrayList<>();
//			List<Item> itemListBy3 = null;
//			for (int i = 0; i < itemList.size(); i++) {
//				if (i == 0 || i % 3 == 0) {
//					itemListBy3 = new ArrayList<Item>();
//					itemListList.add(itemListBy3);
//				}
//				itemListBy3.add(itemList.get(i));
//			}
//			return itemListList;
//		}
	/**
	 * 商品を曖昧検索、値段順で並び替える.
	 * 
	 * @param name 名前
	 * @return 曖昧検索結果
	 */
	public List<Item> findByNameAndSort(SortForm sortForm) {
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
		
		//曖昧検索、該当商品なければ全件、あれば該当商品のみ
//		List<Item> itemList = null;
//		if (searchName == null || searchName.equals("")) {
//			itemList = repository.findAll();
//			System.out.println("全件検索Ser : " + itemList);
//		} else {
//			itemList = repository.findByNameAndSort(searchName, sort);
//		}
//		return repository.findByNameAndSort(searchName, sort);
//	}
//		List<List<Item>> itemListList = devideItemsBy3(itemList);
//		//曖昧検索の結果
//		return itemListList;
//		}

//		startNumber = SearchStartNumber(sortForm);
//		if ("expensive".equals(sort)) {
//			itemList = repository.findByNameAndSort(searchName, sort, startNumber);
//		} else if("cheap".equals(sort)) {
//			itemList = repository.findByNameAndSort(searchName, sort, startNumber);
//		} else {
//			itemList = repository.findAll(startNumber);
//		}

	/**
	 * 値段が高い順、低い順で商品を検索する.
	 * 
	 * @return 値段が高い順、低い順の商品一覧
	 */
//	public List<List<Item>> sortItemByMoney(SortForm sortForm,Integer startNumber) {
//		String sort = sortForm.getSort();
//		startNumber = SearchStartNumber(sortForm);
//		List<Item> itemList = null;
//		if ("expensive".equals(sort)) {
//			itemList = repository.orderByExpensiveItem(startNumber);
//		} else if("cheap".equals(sort)) {
//			itemList = repository.orderByCheapItem(startNumber);
//		} else {
//			itemList = repository.findAll(startNumber);
//		}
//		List<List<Item>> itemListList = devideItemsBy3(itemList);
//		return itemListList;
//	}

	/**
	 * 商品一覧の総件数を検索.
	 * 
	 * @return 商品一覧の総件数
	 */
//	public Integer count() {
//		return repository.count();
//	}


}
