package jp.co.example.ecommerce_b.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.service.ShowOrderItemService;

/**
 * ショッピングカート画面を表示するコントローラ.
 * 
 * @author sakai
 *
 */
@Controller
@RequestMapping("/ShowOrderItem")
public class ShowOrderItemContoroller {

	@Autowired
	private ShowOrderItemService showOrderItemService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * ショッピングカートの中身を表示する.
	 * 
	 * @param model モデル
	 * @param loginUser ユーザーID
	 * @return 「ショッピングカート画面」を表示
	 */
	@RequestMapping("")
	public String showOrderItem(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId;
		if (loginUser.getUser().getId().equals(null)) {
			userId = Integer.parseInt(session.getId());
		} else {
			userId = loginUser.getUser().getId();
		}
		List<Order> orderList = showOrderItemService.findByUserIdAndStatus(userId);
		model.addAttribute("orderList", orderList);
		return "cart_list";
	}
	
}
