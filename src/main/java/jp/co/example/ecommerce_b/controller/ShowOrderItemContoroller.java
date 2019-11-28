package jp.co.example.ecommerce_b.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.service.CountInCartService;
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

	@Autowired
	private CountInCartService countInCartService;

	/**
	 * ショッピングカートの中身を表示する.
	 * 
	 * @param model     モデル
	 * @param loginUser ユーザーID
	 * @return 「ショッピングカート画面」を表示
	 */
	@RequestMapping("")
	public String showOrderItem(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId;
		if (loginUser == null) {
			userId = (Integer) session.getAttribute("userId");
		} else {
			userId = loginUser.getUser().getId();
		}
		List<Order> orderList = showOrderItemService.findByUserIdAndStatus(userId);
		//ショッピングカートの中身が0件か1件以上の場合
		Order order = new Order();
		order = orderList.get(0);
		//ショッピングカートの中身が0件
		if (CollectionUtils.isEmpty(order.getOrderItemList())) {
			String str = "カートに商品がありません";
			model.addAttribute("str", str);
		//ショッピングカートの中身が1件以上
		} else {
			model.addAttribute("order", order);
		}
		model.addAttribute("orderList", orderList);
		// 現在のショッピングカート内の数量表示用
		Integer countInCart = countInCartService.countInCart(loginUser);
		model.addAttribute("countInCart", countInCart);
		return "cart_list";
	}

}
