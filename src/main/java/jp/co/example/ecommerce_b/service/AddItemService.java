package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.OrderTopping;
import jp.co.example.ecommerce_b.repository.OrderItemRepository;
import jp.co.example.ecommerce_b.repository.OrderToppingRepository;

/**
 * 商品追加の機能.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class AddItemService {

	@Autowired
	public OrderItemRepository orderItemRepository;
	
	@Autowired
	public OrderToppingRepository orderToppingRepository;
	
	/**
	 * 注文された商品を挿入する.
	 * 
	 * @param orderItem　注文した商品
	 */
	public void addItem(OrderItem orderItem) {
		orderItemRepository.addItem(orderItem);;
	}
}
