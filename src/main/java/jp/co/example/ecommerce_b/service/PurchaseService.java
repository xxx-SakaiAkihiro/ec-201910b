package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.repository.OrderItemRepository;

/**
 * 商品購入を行うサービス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class PurchaseService {

	@Autowired
	private OrderItemRepository repository;
	
	/**
	 * 注文を挿入する.
	 * 
	 * @param order 注文した商品
	 */
	public void inserOrder(Order order) {
		repository.insert(order);
	}
}
