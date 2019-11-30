package jp.co.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.repository.OrderRepository;

/**
 * 注文履歴を表示するサービス.
 * 
 * @author sakai
 *
 */
@Service
@Transactional
public class ShowHistoryItemService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文履歴画面の中身を表示する.
	 * 
	 * @param userId ユーザーID
	 * @return
	 */
	public List<Order> showHistoryItem(Integer userId) {
		List<Order> orderList = new ArrayList<>();
		orderList.addAll(orderRepository.findByUserIdAndStatus(userId, 1));
		orderList.addAll(orderRepository.findByUserIdAndStatus(userId, 2));
		return orderList;
	}

}
