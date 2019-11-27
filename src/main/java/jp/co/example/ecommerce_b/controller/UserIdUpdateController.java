package jp.co.example.ecommerce_b.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.repository.OrderRepository;

@Controller
@RequestMapping("/updateId")
public class UserIdUpdateController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderRepository orderRepository;
	/**
	 * セッションIDを使用し、注文テーブルのステータスが0の注文情報を取得する。その後ユーザIDを更新する
	 * @param user ユーザー
	 */
	
	public String updateUserId(LoginUser loginUser) {
		int status = 0;
		String source = session.getId();
		Integer sesssionId = source.hashCode();
		List<Order> orderList = orderRepository.findByUserIdAndStatus(sesssionId,status);
		Order order = orderList.get(0);
		order.setUserId(loginUser.getUser().getId());
		orderRepository.update(order);
		return "/ShowOrderItem";
	}
}
