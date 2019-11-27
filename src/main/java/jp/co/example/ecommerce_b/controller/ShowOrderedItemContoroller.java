package jp.co.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Order;
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


//	@RequestMapping("")
//	public String showOrderedItem(Model model, @AuthenticationPrincipal LoginUser loginuser) {
//		List<Order> orderItemList = (List<Order>) showOrderedItemService.showOrderedItem(loginuser.getUser().getId());
//		model.addAttribute("orderItemList", orderItemList);
//		return "order_confirm";
//
//	}

}
