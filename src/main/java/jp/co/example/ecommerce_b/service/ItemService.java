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
	public List<List<Item>> showItemListFindByName(String name) {
		List<Item> itemList = null;
		if (name == null || name.equals("")) {
			itemList = repository.findAll();
		} else {
			itemList = repository.findByName(name);
		}
		List<List<Item>> itemListList = new ArrayList<>();
		ArrayList<Item> itemListBy3 = null;
		for (int i = 0; i < itemList.size(); i++) {
			if(i == 0 || i % 3 == 0) {
				itemListBy3 = new ArrayList<Item>();
				itemListList.add(itemListBy3);			
			}
			itemListBy3.add(itemList.get(i));
		}
		return itemListList;
	}
	
//	public Page<Item> showListPaging(int page,int size,List<Item> itemList){
//		page--;
//		int startItemCount = page * size;
//		List<Item> list;
//		if(itemList.size() > startItemCount) {
//			list = Collections.emptyList();
//		} else {
//			int toIndex = Math.min(startItemCount + size,itemList.size());
//			list = itemList.subList(startItemCount, toIndex);
//		}
//		
//		Page<Item> itemPage = new PageImpl<Item>(list,PageRequest.of(page, size),itemList.size());
//		return itemPage;
//	}

}
