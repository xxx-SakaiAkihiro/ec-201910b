package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.repository.OrderRepository;

/**
 * 商品購入を行うサービス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class PurchaseService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MailSender mailSender;
	
	/**
	 * 注文を挿入する.
	 * 
	 * @param order 注文した商品
	 */
	public void insertOrder(Order order) {
		orderRepository.insert(order);
	}
	/**
	 * 注文情報をを購入後の情報へ更新する.
	 * 
	 * @param order 注文情報
	 */
	public void purchase(Order order) {
		orderRepository.update(order);
	} 
	
	public void sendMail() {
		SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom("toy_ec_201910b@gmail.com");
        msg.setTo("<メールアドレス>");
        msg.setSubject("ご注文内容の確認");//タイトルの設定
        msg.setText("ラクラクトイテストメール"); //本文の設定

        this.mailSender.send(msg);
    }
	
}
