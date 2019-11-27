package jp.co.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.User;
import jp.co.example.ecommerce_b.repository.OrderRepository;
import jp.co.example.ecommerce_b.repository.UserRepository;

/**
 * ログイン後のユーザーに権限情報を付与するためのサービスクラス.
 * @author taro
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	/**
	 * DBから情報を得るためのリポジトリ
	 */
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private HttpSession session;
	
	/**
	 *ログインを行うと同時に注文テーブルのユーザーIDを更新する。
	 */
	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException{
		User user = userRepository.findByEmail(mailAddress);
		if(user == null) {
			throw new UsernameNotFoundException("そのメールアドレスは登録されていません。");
		}
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		LoginUser loginUser = new LoginUser(user,authorityList);
//		updateUserId(user);
		return loginUser;
	}
	
	/**
	 * セッションIDを使用し、注文テーブルのステータスが0の注文情報を取得する。その後ユーザIDを更新する
	 * @param user ユーザー
	 */
	public void updateUserId(User user) {
		int status = 0;
		String source = session.getId();
		Integer sesssionId = source.hashCode();
		Order Order = orderRepository.findByUserIdAndStatus(sesssionId,status);
		Order.setUserId(user.getId());
		orderRepository.update(Order);
	}
}
