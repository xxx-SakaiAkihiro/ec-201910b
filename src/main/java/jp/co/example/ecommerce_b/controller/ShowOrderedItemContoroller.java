package jp.co.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.service.CountInCartService;
import jp.co.example.ecommerce_b.service.ShowOrderedItemService;

/**
 * 注文確認画面を表示するコントローラ.
 * 
 * @author riho.ikeda
 *
 */
@Controller
@RequestMapping("/ShowOrderedItem")
public class ShowOrderedItemContoroller {

	@Autowired
	private ShowOrderedItemService showOrderedItemService;
	
	@Autowired
	private CountInCartService countInCartService;

	/**
	 * 注文画面の中身を表示する.
	 * 
	 * @param model モデル
	 * @param loginUser ユーザーID
	 * @return 「注文確認画面」を表示
	 */
    @RequestMapping("")
    public String showOrderedItem(Model model,@AuthenticationPrincipal LoginUser loginuser) {
		List<Order> orderList = showOrderedItemService.showOrderedItem(loginuser.getUser().getId());
		model.addAttribute("orderList", orderList);
		
		Integer countInCart = countInCartService.countInCart(loginuser);
		model.addAttribute("countInCart",countInCart);
       return "order_confirm";

	}

}
