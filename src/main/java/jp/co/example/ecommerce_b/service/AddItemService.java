package jp.co.example.ecommerce_b.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.OrderTopping;
import jp.co.example.ecommerce_b.form.OrderItemForm;
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
	
	@Autowired
	public HttpSession session;


	/**
	 * 注文された商品を挿入する.
	 * 
	 * @param orderItem 注文した商品
	 */
	public void addItem(OrderItemForm orderItemForm) {
		
		String source = session.getId();
		Integer userId = source.hashCode();
		
		int status = 0;
		Order order = null;
		List<Order> searchOrderList = orderrepository.findByUserIdAndStatus(userId, status);
		if (searchOrderList.isEmpty()) {
			order = new Order();
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);
			// orderをインサート
			orderrepository.insert(order);

			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(Integer.parseInt(orderItemForm.getItemId()));
			orderItem.setOrderId(order.getId());
			orderItem.setQuantity(Integer.parseInt(orderItemForm.getQuantity()));
			orderItem.setSize(orderItemForm.getSize());
			// orderIteｍをインサート
			orderItemRepository.insert(orderItem);

			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setOrderItemId(orderItem.getId());
			for (Integer toppingId : orderItemForm.getOrderToppingList()) {
				orderTopping.setToppingId(toppingId);
				// orderToppingをインサート
				orderToppingRepository.insert(orderTopping);
			}
		} else {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(Integer.parseInt(orderItemForm.getItemId()));
			Order existOrder = searchOrderList.get(0);
			Integer existOrderId = existOrder.getId();
			orderItem.setOrderId(existOrderId);
			orderItem.setQuantity(Integer.parseInt(orderItemForm.getQuantity()));
			orderItem.setSize(orderItemForm.getSize());
			// orderIteｍをインサート
			orderItemRepository.insert(orderItem);

			OrderTopping orderTopping = new OrderTopping();
			for (Integer toppingId : orderItemForm.getOrderToppingList()) {
				orderTopping.setOrderItemId(toppingId);
				orderTopping.setToppingId(toppingId);
				// orderToppingをインサート
				orderToppingRepository.insert(orderTopping);
				System.out.println(11);
			}
		}
		System.out.println(10);
	}
}
