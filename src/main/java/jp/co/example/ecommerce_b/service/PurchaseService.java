package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.repository.OrderRepository;

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
	private OrderRepository repository;
	
	/**
	 * 注文を挿入する.
	 * 
	 * @param order 注文した商品
	 */
	public void insertOrder(Order order) {
		repository.insertOrder(order);
	}
}
