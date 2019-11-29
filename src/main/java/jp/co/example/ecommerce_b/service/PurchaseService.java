package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import jp.co.example.ecommerce_b.domain.CreditCard;
import jp.co.example.ecommerce_b.domain.CreditCardData;
import jp.co.example.ecommerce_b.domain.LoginUser;
//import jp.co.example.ecommerce_b.domain.CreditCard;
//import jp.co.example.ecommerce_b.domain.CreditCardData;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.OrderItem;
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

	public void purchase(Order order,@AuthenticationPrincipal LoginUser loginUser) {
		orderRepository.orderUpdate(order);
		sendMail(loginUser,order);
	} 
	
	public void sendMail(LoginUser loginUser,Order order) {
		SimpleMailMessage msg = new SimpleMailMessage();
		String br = System.getProperty("line.separator");
        msg.setFrom("pepperoni.4.eg@gmail.com");
        msg.setTo("pepperoni.4.eg@gmail.com");
        msg.setSubject("【ラクラクトイ】ご注文内容の確認");//タイトルの設定
        String toptext = "----------------------------------------------------------------------------------------" + br
        		+ loginUser.getUser().getName() + "様" + br
        		+ "ラクラクトイをご利用いただきまして、誠にありがとうございます。" + br
        		+ br
        		+ "ご注文内容を以下にご案内いたします。" + br
        		+ br
        		+ "----------------------------------------------------------------------------------------" + br
        		+ "■ご注文内容" + br
        		+ "----------------------------------------------------------------------------------------" + br
        		+ br;
        String itemText = "■商品名:";
        for (OrderItem orderItem  : order.getOrderItemList()) {
			itemText = orderItem.getItem().getName() + br;
		}
        itemText = "■金額:" + order.getTotalPrice()+ br;
        itemText = "■支払い方法:" + order.getPaymentMethod() + br;
        itemText = "----------------------------------------------------------------------------------------" + br;
        String lastText = "代金引換をご利用のお客様は以下振込先へのお振込をよろしくお願いいたします。" + br
        		+ "■振込先" + br
        		+ "銀行名:三菱東京UFJ銀行" + br
        		+ "支店名:新宿新都心支店" + br
        		+ "口座番号: 普通 9999999 " + br
        		+ "口座名義: カ）ラクラクトイ " + br
        		+ br
        		+ "今後ともラクラクトイをよろしくお願いいたします";
        
        msg.setText(toptext + itemText + lastText);
        

        this.mailSender.send(msg);
    }
	

    /** クレジットカードAPI リクエストURL */
    private static final String URL = "http://192.168.56.101:8080/sample-credit-card-web-api/credit-card/payment";

    public CreditCardData creditCardCall(CreditCard creditCard  ) {
        return restTemplate.postForObject(URL, creditCard, CreditCardData.class);
//        postForObject(URL, CreditCardData.class, creditCard);
    }
   
	
	
	
	
}
