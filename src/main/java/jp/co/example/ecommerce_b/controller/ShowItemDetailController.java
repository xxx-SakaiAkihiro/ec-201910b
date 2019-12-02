package jp.co.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.LoginUser;
import jp.co.example.ecommerce_b.domain.Want;
import jp.co.example.ecommerce_b.repository.WantsRepository;
import jp.co.example.ecommerce_b.service.CountInCartService;
import jp.co.example.ecommerce_b.service.ShowItemDetailService;

/**
 * @author riho.ikeda
 *
 *商品詳細を表示するコントローラ.
 */
@Controller
@RequestMapping("/ShowItemDetail")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService showitemDetailService;
	
	@Autowired
	private CountInCartService countInCartService;
	
	@Autowired
	private WantsRepository wantsRepository;

	/**
	 * 商品詳細を表示する.
	 * 
	 * @param id ID
	 * @return 商品1件の検索結果
	 */
	@RequestMapping("")
	public String showItemDetail(Integer id, Model model,@AuthenticationPrincipal LoginUser loginUser) {
		Item item = showitemDetailService.showItemDetail(id);
		model.addAttribute("item",item);
		
		Integer countInCart = countInCartService.countInCart(loginUser);
		model.addAttribute("countInCart",countInCart);
		
		if(loginUser == null ) {
			model.addAttribute("want","want");
			return "item_detail";
		}
		List<Want> wantList = wantsRepository.findByUserIdAndItemId(loginUser.getUser().getId(), item.getId());
		if(wantList.size() == 0) {
			model.addAttribute("want","want");
		} else {
			model.addAttribute("notWant","notWant");
		}
		return "item_detail";

	}

}
