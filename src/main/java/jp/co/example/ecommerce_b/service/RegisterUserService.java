package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.User;
import jp.co.example.ecommerce_b.repository.RegisterUserRepository;

/**
 * ユーザ情報を登録するリポジトリ.
 * 
 * @author sakai
 *
 */
@Service
@Transactional
public class RegisterUserService {

	@Autowired
	private RegisterUserRepository registerUserRepository;
	
	/**
	 * ユーザ情報を登録する.
	 * 
	 * @param user
	 */
	public void insert(User user) {
		registerUserRepository.insert(user);
	}
	
}
