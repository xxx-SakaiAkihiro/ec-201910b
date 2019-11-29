package jp.co.example.ecommerce_b.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.service.ResetPasswordService;

/**
 * パスワード再設定用のコントローラー
 * @author taro
 *
 */
@Controller
@RequestMapping("/reset")
public class ResetPasswordController {
	private static int TOKEN_LENGTH = 3;// 16*2=32バイト
	
	@Autowired
	private ResetPasswordService resetPasswordService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Autowired
	private HttpSession session;
	// 32バイトのCSRFトークンを作成
	
	@RequestMapping("")
	public String inputEmail() {
		return "input_email";
	}
	
	@RequestMapping("/token")
	public String getCsrfToken(String registeredEmail) {
		byte token[] = new byte[TOKEN_LENGTH];
		StringBuffer buf = new StringBuffer();
		SecureRandom random = null;

		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			random.nextBytes(token);

			for (int i = 0; i < token.length; i++) {
				buf.append(String.format("%02x", token[i]));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String newtoken = buf.toString(); 
		System.out.println(newtoken);
		session.setAttribute("token", newtoken);
		session.setAttribute("registeredEmail", registeredEmail);
		resetPasswordService.sendMail(newtoken,registeredEmail);
		return "token_form";
	}
	
	@RequestMapping("/updatePass")
	public String updatePass(String token) {
		if ( token.equals(session.getAttribute("token"))) {
			return "reset_password";
		}
		return "token_form";
	}
	
	@RequestMapping("/setPass")
	public String setPass(String newPassword,String newPassword2) {
		if(newPassword.equals(newPassword2)) {
			String email = (String) session.getAttribute("registeredEmail");
			String encodePassword = passwordEncoder.encode(newPassword);
			resetPasswordService.updatePass(email, encodePassword);
			return "redirect:/login";
		}
		return "reset_password";
	}
}
