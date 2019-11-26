package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.repository.OrderItemRepository;
import jp.co.example.ecommerce_b.repository.OrderRepository;
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
	public OrderRepository orderrepository;

	@Autowired
	public OrderItemRepository orderItemRepository;
	
	@Autowired
	public OrderToppingRepository orderToppingRepository;
	
	/**
	 * 注文された商品を挿入する.
	 * 
	 * @param orderItem　注文した商品
	 */
	public void addItem(Order order,OrderItem orderItem,Integer orderTopping) {
//		order.setStatus(0);
//		
//		//userIdを生成
//		order.setUserId(userId);
//		
//		if(orderrepository.findByUserIdAndStatus() == null) {
//			orderrepository.insert(order);
//			orderToppingRepository.insert(orderTopping);
//			orderItemRepository.insert(orderItem);;			
//		} else {
//			orderToppingRepository.insert(orderTopping);
//			orderItemRepository.insert(orderItem);;			
//			
//		}
		
	}
}
