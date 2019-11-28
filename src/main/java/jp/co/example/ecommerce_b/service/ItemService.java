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
		System.out.println(pageNumber);
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

//	/**
//	 * 値段が高い順で商品を検索する.
//	 * 
//	 * @param priceM Mサイズの値段
//	 * @return 値段が高い順の商品一覧
//	 */
//	public List<Item> orderByExpensiveItem(Integer priceL) {
//		return repository.orderByExpensiveItem(priceL);
//	}
//	/**
//	 * 値段が低い順で商品を検索する.
//	 * 
//	 * @param priceM Mサイズの値段
//	 * @return 値段が高い順の商品一覧
//	 */
//	public List<Item> orderByCheapItem(Integer priceM) {
//		return repository.orderByCheapItem(priceM);
//	}

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
