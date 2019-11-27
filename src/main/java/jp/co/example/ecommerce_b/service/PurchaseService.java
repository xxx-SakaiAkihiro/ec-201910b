package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.domain.LoginUser;
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
	
	public void sendMail(LoginUser loginUser) {
		SimpleMailMessage msg = new SimpleMailMessage();
		String br = System.getProperty("line.separator");
        msg.setFrom("pepperoni.4.eg@gmail.com");
        msg.setTo("pepperoni.4.eg@gmail.com");
        msg.setSubject("【ラクラクトイ】ご注文内容の確認");//タイトルの設定
        msg.setText("--------------------------------------------" + br
        		+ loginUser.getUser().getName() + "様" + br
        		+ "ラクラクトイをご利用いただきまして、誠にありがとうございます。" + br
        		+ br
        		+ "ご注文内容を以下にご案内いたします。" + br
        		+ br
        		+ "--------------------------------------------" + br
        		+ "■ご注文内容" + br
        		+ "--------------------------------------------" + br
        		+ br
        		+ "商品名:" + br
        		+ "価格(税込み):" + br
        		+ "数量:" + br
        		+ "トッピング:" + br
        		+ "お支払い方法:" + br
        		+ "--------------------------------------------" + br
        		+ br
        		+ "今後ともラクラクトイをよろしくお願い申し上げます。"
        		+ ""
        		+ ""
        		+ ""
        		+ "");

        this.mailSender.send(msg);
    }
	
}
