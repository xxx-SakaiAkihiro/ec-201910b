package jp.co.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.User;
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
		return loginUser;
	}
	
}
