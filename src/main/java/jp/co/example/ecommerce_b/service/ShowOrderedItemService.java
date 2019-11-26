package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.repository.OrderItemRepository;
import jp.co.example.ecommerce_b.repository.OrderRepository;

/**
 * 注文確認を表示するサービス.
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class ShowOrderedItemService {
	/*@Autowired
	private OrderRepository orderRepository;
	
	public Order showOrderedItem(Integer userId) {
		
		
	return orderRepository.findByUserIdAndStatus(userId,1);
		
		
	} */

}
