package jp.co.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * @return　商品数に対し必要なページ数のリスト
	 */
	public List<Integer> NeedPage() {
		Integer count = repository.count();
		int maxPageNumber = 0;
		List<Integer> pageNumbers = new ArrayList<Integer>();
		if (count % 6 != 0) {
			maxPageNumber = count / 6 + 1;
		} else {
			maxPageNumber = count / 6;
		}
		for (int i = 1; i <= maxPageNumber; i++) {
			pageNumbers.add(i);
		}
		return pageNumbers;
	}
	
		/**
		 * 商品の表示開始番号を固定する.
		 * 
		 * @param sortForm ソートフォーム
		 * @return 商品の表示開始番号
		 */
		public Integer SearchStartNumber(SortForm sortForm) {
		Integer startNumber = null;
		Integer pageNumber = sortForm.getPageNumber();
		if (pageNumber == null || pageNumber == 1) {
			startNumber = 0;
		} else {
			startNumber = (pageNumber - 1) * 6;
		}
		
		return startNumber;
	}

		/**
		 * 3つ区切りで商品を表示.
		 * 
		 * @param itemList アイテム商品一覧
		 * @return 3つ区切りで商品表示
		 */
		private List<List<Item>> devideItemsBy3(List<Item> itemList) {
			List<List<Item>> itemListList = new ArrayList<>();
			List<Item> itemListBy3 = null;
			for (int i = 0; i < itemList.size(); i++) {
				if (i == 0 || i % 3 == 0) {
					itemListBy3 = new ArrayList<Item>();
					itemListList.add(itemListBy3);
				}
				itemListBy3.add(itemList.get(i));
			}
			return itemListList;
		}
	/**
	 * 曖昧検索をする.
	 * 
	 * @param name 名前
	 * @return 曖昧検索結果
	 */
	public List<List<Item>> showItemListFindByName(SortForm sortForm) {
		String searchName = sortForm.getSearchName();
		Integer startNumber = SearchStartNumber(sortForm);
		List<Item> itemList = null;
		if (searchName == null || searchName.equals("")) {
			itemList = repository.findAll(0);
		} else if(searchName.equalsIgnoreCase(searchName)){
			itemList = repository.findByName(searchName,startNumber);
		}
		List<List<Item>> itemListList = devideItemsBy3(itemList);
		return itemListList;
	}

	/**
	 * 値段が高い順、低い順で商品を検索する.
	 * 
	 * @return 値段が高い順、低い順の商品一覧
	 */
	public List<List<Item>> sortItemByMoney(SortForm sortForm,Integer startNumber) {
		String sort = sortForm.getSort();
		startNumber = SearchStartNumber(sortForm);
		List<Item> itemList = null;
		if (sort.equals("expensive")) {
			itemList = repository.orderByExpensiveItem(startNumber);
		} else if(sort.equals("cheap")) {
			itemList = repository.orderByCheapItem(startNumber);
		} else {
			itemList = repository.findAll(startNumber);
		}
		List<List<Item>> itemListList = devideItemsBy3(itemList);
		return itemListList;
	}

	/**
	 * 商品一覧の総件数を検索.
	 * 
	 * @return 商品一覧の総件数
	 */
	public Integer count() {
		return repository.count();
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
		return repository.findAll(0);

	}

}
