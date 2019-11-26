package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.repository.OrderItemRepository;

/**
 * カートの商品を削除するサービス.
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class DeleteItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	/**
	 * カートの商品を削除するサービス.
	 * 
	 * @param orderItemId ID
	 */
	public void deleteItem(Integer orderItemId) {
		orderItemRepository.deleteById(orderItemId);
	}

}
