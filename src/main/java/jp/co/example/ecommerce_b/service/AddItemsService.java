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
public class AddItemsService {

	@Autowired
	public OrderItemRepository orderItemRepository;
	
	@Autowired
	public OrderToppingRepository orderToppingRepository;
	
	/**
	 * 注文された商品を挿入する.
	 * 
	 * @param orderItem　注文した商品
	 */
	public void insertOrderItem(OrderItem orderItem) {
		orderItemRepository.insert(orderItem);;
	}
	/**
	 * 注文されたトッピングを挿入する.
	 * 
	 * @param orderTopping 注文したトッピング
	 */
	public void insertOrderTopping(OrderTopping orderTopping) {
		orderToppingRepository.insert(orderTopping);
	}
	
}
