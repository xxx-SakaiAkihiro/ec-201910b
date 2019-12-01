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

@Controller
@RequestMapping("/wants")
public class WantsController {
	
	@Autowired
	private WantsRepository wantsRepository;
	
	@Autowired
	private CountInCartService countInCartService;
	
	@RequestMapping("")
	public String wants(Integer itemId,@AuthenticationPrincipal LoginUser loginUser) {
		List<Want> wantList = wantsRepository.findByUserIdAndItemId(loginUser.getUser().getId(), itemId);
		if(wantList.size() == 0) {
			wantsRepository.insert(loginUser.getUser().getId(), itemId);
		} else {
			wantsRepository.delete(loginUser.getUser().getId(), itemId);
		}
		return "redirect:/ShowItemDetail?id=" + itemId;
	}
	
	@RequestMapping("/showWantList")
	public String showWantList(@AuthenticationPrincipal LoginUser loginUser,Model model) {
		List<Item> itemList = wantsRepository.findByUserId(loginUser.getUser().getId());
		if(itemList.size() == 0 ) {
			String str = "欲しいものリストに登録されている商品はありません。商品詳細画面より登録できます。";
			model.addAttribute("str",str);
		}
		model.addAttribute("itemList",itemList);
		
		Integer countInCart = countInCartService.countInCart(loginUser);
		model.addAttribute("countInCart", countInCart);
		
		return "want_list.html";
	}
}
