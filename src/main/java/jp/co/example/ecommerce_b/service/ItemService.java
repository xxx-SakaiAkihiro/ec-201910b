package jp.co.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Item;
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
	 * 曖昧検索をする.
	 * 
	 * @param name 名前
	 * @return 曖昧検索結果
	 */
	public List<List<Item>> showItemListFindByName(String name, Integer pageNumber) {
		Integer pageCount = 0;
		if (pageNumber == null || pageNumber == 1) {
			pageCount = 0;
		} else {
			pageCount = (pageNumber - 1) * 6;
		}
		List<Item> itemList = null;
		if (name == null || name.equals("")) {
			itemList = repository.findAll(pageCount);
		} else {
			itemList = repository.findByName(name);
		}
		List<List<Item>> itemListList = devideItemsBy3(itemList);
		return itemListList;
	}

	/**
	 * 3つ区切りで商品を表示.
	 * 
	 * @param itemList アイテム商品一覧
	 * @return 3つ区切りで商品表示
	 */
	private List<List<Item>> devideItemsBy3(List<Item> itemList) {
		List<List<Item>> itemListList = new ArrayList<>();
		ArrayList<Item> itemListBy3 = null;
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
	 * 値段が高い順、低い順で商品を検索する.
	 * 
	 * @return 値段が高い順、低い順の商品一覧
	 */
	public List<List<Item>> sortByMoneyItem(String sort) {
		System.out.println("sort :" + sort);
		List<List<Item>> itemListList = null;
		List<Item> itemList = null;
		if (sort.equals("expensive")) {
			System.out.println(11111);
			itemList = repository.orderByExpensiveItem();
		} else {
			System.out.println(2222);
			itemList = repository.orderByCheapItem();
		}

		itemListList = devideItemsBy3(itemList);

//		System.out.println("itemListList : " + itemListList);
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

	public List<Item> itemList() {
		return repository.findAll(0);

	}

}
