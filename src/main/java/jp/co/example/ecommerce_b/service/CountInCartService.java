package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.repository.OrderRepository;

/**
 * ログインユーザーのIDでカート内の件数を取得するサービスクラス.
 * @author taro
 *
 */
@Service
@Transactional
public class CountInCartService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * ログインユーザーのIDでカート内の件数を取得するサービス
	 * @param loginUser　ログインユーザー
	 * @return　件数
	 */
	public Integer countInCart(@AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = loginUser.getUser().getId();
		return orderRepository.countInCart(userId);
	}
}
