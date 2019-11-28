package jp.co.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
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

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文確認画面の中身を表示する.
	 * 
	 * @param userId ユーザーID
	 * @return
	 */
	public List<Order> showOrderedItem(Integer userId) {
		return orderRepository.findByUserIdAndStatus(userId, 0);
	}

}
