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
			System.out.println(1);
			int status = 0;
			System.out.println(2);
			Integer sessionUserId = (Integer) session.getAttribute("userId");
			System.out.println(3);
			//②ログインユーザーのorderを取得(orderは1つあるあるいは、無い。)
			List<Order> loginUserOrderList = orderRepository.findByUserIdAndStatus(loginUser.getUser().getId(),status);
			System.out.println(4);
			Integer loginUserOrderId;
			System.out.println(5);
			
			if ( loginUserOrderList.size() == 0 ) {
				System.out.println(6);
				//ログインユーザーのorderがなければ下記を実行
				//仮ユーザーのorderのuser_idをログインユーザーのuser_idに更新
				Integer loginUserId = loginUser.getUser().getId();
				System.out.println(7);
				orderRepository.updateUserId(loginUserId, sessionUserId);
				System.out.println(8);
			} else {
				//ログインユーザーのorderがあれば、下記を実行
				loginUserOrderId = loginUserOrderList.get(0).getId();
				System.out.println(9);
				//①仮ユーザーのorder_idを取得
				List<Order> orderList = orderRepository.findByUserIdAndStatus(sessionUserId,status);
				System.out.println(10);
				if(orderList.size() == 0 ) {
					return "forward:/ShowOrderItem";
				}
				Order temporalOrder = orderList.get(0);
				System.out.println(11);
				Integer temporalOrderId = temporalOrder.getId();
				System.out.println(12);
				//③order_itemsの仮ユーザーのorder_idをログインユーザーのorder_idに更新
				orderItemRepository.update(temporalOrderId, loginUserOrderId);
				System.out.println(13);
				//④仮ユーザーのorderを削除
				orderRepository.deleteById(temporalOrderId);
				System.out.println(14);
			}
			System.out.println(15);
		} 
			return "forward:/showItem";
	}
}
