package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import jp.co.example.ecommerce_b.domain.CreditCard;
import jp.co.example.ecommerce_b.domain.CreditCardData;
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
	
	@Autowired
	RestTemplate restTemplate;
	
	/**
	 * 注文を挿入する.
	 * 
	 * @param order 注文した商品
	 */
	public void insertOrder(Order order) {
		orderRepository.insert(order);
	}
	/**
	 * 注文情報を購入後の情報へ更新する.
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
	

    /** クレジットカードAPI リクエストURL 
    private static final String URL = "http://172.16.0.13:8080/sample-credit-card-web-api/credit-card/payment";

    public CreditCardData creditCardCall(CreditCard creditCard  ) {
        return restTemplate.getForObject(URL, CreditCardData.class, creditCard);
    }
   */
	
	
	
	
}
