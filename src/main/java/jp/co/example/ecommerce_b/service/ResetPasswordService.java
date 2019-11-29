package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.repository.UserRepository;

@Service
@Transactional
public class ResetPasswordService {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	public void sendMail(String token,String registeredEmail) {
		SimpleMailMessage msg = new SimpleMailMessage();
		String br = System.getProperty("line.separator");
        msg.setFrom("pepperoni.4.eg@gmail.com");
        msg.setTo(registeredEmail);
        msg.setSubject("【ラクラクトイ】パスワード再設定用メール");//タイトルの設定
        msg.setText("6桁の英数字:" + token + br
        		   + "ブラウザの画面で英数字を入力後パスワードを再設定してください。");
        this.mailSender.send(msg);
    }
	
	public void updatePass(String email,String password) {
		userRepository.updatePass(email, password);
	}
}
