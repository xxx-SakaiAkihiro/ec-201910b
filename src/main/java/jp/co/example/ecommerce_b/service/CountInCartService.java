package jp.co.example.ecommerce_b.service;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

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
	
	@Autowired
	private HttpSession session;
	
	/**
	 * ログインユーザーのIDでカート内の件数を取得するサービス
	 * @param loginUser　ログインユーザー
	 * @return　件数
	 */
	public Integer countInCart(@AuthenticationPrincipal LoginUser loginUser) {
		Integer userId;
		if ( loginUser != null ) {
			userId = loginUser.getUser().getId();
			System.out.println("ログイン時");
		} else if (session.getAttribute("userId") != null ) {
			userId = (Integer) session.getAttribute("userId");
			System.out.println("セッションID有り");
		} else {
			System.out.println("なにもなし");
			return 0;
		}
		System.out.println("ユーザーID" + userId);
		Integer count = orderRepository.countInCart(userId);
		System.out.println("サービス戻り値" + count);
		return count;
	}
}
