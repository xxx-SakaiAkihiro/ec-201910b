package jp.co.example.ecommerce_b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ログイン画面への操作をするコントローラー
 * @author taro
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping("/toLogin")
	public String toLogin(Model model,@RequestParam(required = false) String error) {
		System.out.println("login error:" + error);
		if(error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage","メールアドレスまたはパスワードが不正です");
		}
		return "login";
	}
	

}
