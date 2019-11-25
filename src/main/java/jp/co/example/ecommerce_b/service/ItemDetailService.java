package jp.co.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.Topping;
import jp.co.example.ecommerce_b.repository.ItemRepository;
import jp.co.example.ecommerce_b.repository.ToppingRepository;

/**
 * @author riho.ikeda
 *
 *商品詳細を表示するサービス.
 */
@Service
@Transactional
public class ItemDetailService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * 商品詳細を表示する.
	 * 
	 * @param id ID
	 * @return 商品1件の検索結果
	 */
	public Item load(Integer id) {
		Item item = itemRepository.load(id);
		List<Topping> toppingList = toppingRepository.findAll();
		item.setToppingList(toppingList);
		return item;

	}

}
