package jp.co.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.service.CountInCartService;
import jp.co.example.ecommerce_b.service.ShowHistoryItemService;

/**
 * 注文履歴画面を表示するコントローラ.
 * 
 * @author sakai
 *
 */
@Controller
@RequestMapping("/ShowHistoryItem")
public class ShowHistoryItemContoroller {

	@Autowired
	private ShowHistoryItemService showHistoryItemService;

	@Autowired
	private CountInCartService countInCartService;

	/**
	 * 注文履歴画面の中身を表示する.
	 * 
	 * @param model     モデル
	 * @param loginUser ユーザーID
	 * @return 「注文履歴画面」を表示
	 */
	@RequestMapping("")
	public String showHistoryItem(Model model, @AuthenticationPrincipal LoginUser loginuser) {
		List<Order> orderList = showHistoryItemService.showHistoryItem(loginuser.getUser().getId());
		// 注文履歴の中身が0件の場合
		if (orderList.size() == 0 || CollectionUtils.isEmpty(orderList.get(0).getOrderItemList())) {
			String str = "注文履歴がありません";
			model.addAttribute("str", str);
			// 注文履歴の中身が1件以上の場合
		} else {
			model.addAttribute("order", orderList.get(0));
			model.addAttribute("orderList", orderList);
		}

		Integer countInCart = countInCartService.countInCart(loginuser);
		model.addAttribute("countInCart", countInCart);
		return "order_history";
	}

}
