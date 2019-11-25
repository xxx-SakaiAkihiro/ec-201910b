package jp.co.example.ecommerce_b.service;

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
	 * 商品一覧検索する.
	 * 
	 * @return 商品一覧
	 */
	public List<Item> findAll(){
		return repository.findAll();
	}
	/**
	 * 曖昧検索をする.
	 * 
	 * @param name 名前
 	 * @return 曖昧検索結果
	 */
	public List<Item> findByName(String name){
		return repository.findByName(name);
	}
	
	
}
