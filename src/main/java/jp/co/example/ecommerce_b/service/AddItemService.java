package jp.co.example.ecommerce_b.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.LoginUser;
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
	public void addItem(OrderItemForm orderItemForm, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId;
		if (loginUser != null) {
			userId = loginUser.getUser().getId();
		} else if (session.getAttribute("userId") != null) {
			userId = (Integer) session.getAttribute("userId");
		} else {
			String source = session.getId();
			userId = source.hashCode();
			session.setAttribute("userId", userId);
		}

		int status = 0;
		Order order = null;

		List<Order> searchOrderList = orderrepository.findByUserIdAndStatus(userId, status);
		List<Integer> orderToppingList = orderItemForm.getOrderToppingList();
		if (searchOrderList.isEmpty()) {
			order = new Order();
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);
			// orderをインサート
			System.out.println(userId);
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
			if (orderToppingList != null) {
				for (Integer toppingId : orderToppingList) {
					orderTopping.setToppingId(toppingId);
					// orderToppingをインサート
					orderToppingRepository.insert(orderTopping);
				}
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
			if (orderToppingList != null) {
				for (Integer toppingId : orderToppingList) {
					orderTopping.setToppingId(toppingId);
					// orderToppingをインサート
					orderToppingRepository.insert(orderTopping);

				}
			}
		}
	}
}
