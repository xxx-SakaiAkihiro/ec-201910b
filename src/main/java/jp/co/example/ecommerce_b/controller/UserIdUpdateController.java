package jp.co.example.ecommerce_b.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.repository.OrderItemRepository;
import jp.co.example.ecommerce_b.repository.OrderRepository;

@Controller
@RequestMapping("/updateId")
public class UserIdUpdateController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	/**
	 * セッションIDを使用し、注文テーブルのステータスが0の注文情報を取得する。その後ユーザIDを更新する
	 * @param user ユーザー
	 */
	@RequestMapping("")
	public String updateUserId(@AuthenticationPrincipal LoginUser loginUser) {
		if (session.getAttribute("userId") != null) {
			
			int status = 0;
			
			Integer sessionUserId = (Integer) session.getAttribute("userId");
			
			//②ログインユーザーのorderを取得(orderは1つあるあるいは、無い。)
			List<Order> loginUserOrderList = orderRepository.findByUserIdAndStatus(loginUser.getUser().getId(),status);
			
			Integer loginUserOrderId;
			
			
			if ( loginUserOrderList.size() == 0 ) {
			
				//ログインユーザーのorderがなければ下記を実行
				//仮ユーザーのorderのuser_idをログインユーザーのuser_idに更新
				Integer loginUserId = loginUser.getUser().getId();
			
				orderRepository.updateUserId(loginUserId, sessionUserId);
			} else {
				//ログインユーザーのorderがあれば、下記を実行
				loginUserOrderId = loginUserOrderList.get(0).getId();
				//①仮ユーザーのorder_idを取得
				List<Order> orderList = orderRepository.findByUserIdAndStatus(sessionUserId,status);
				if(orderList.size() == 0 ) {
					return "forward:/ShowOrderItem";
				}
				Order temporalOrder = orderList.get(0);
				Integer temporalOrderId = temporalOrder.getId();
				//③order_itemsの仮ユーザーのorder_idをログインユーザーのorder_idに更新
				orderItemRepository.update(temporalOrderId, loginUserOrderId);
				//④仮ユーザーのorderを削除
				orderRepository.deleteById(temporalOrderId);
			}
		} 
			return "forward:/";
	}
}
