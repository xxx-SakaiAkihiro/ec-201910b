package jp.co.example.ecommerce_b.domain;

import java.util.List;

public class item {

	/* ID */
	private Integer id;
	/* 名前 */
	private String name;
	/* 説明 */
	private String description;
	/* Mの価格 */
	private Integer price_m;
	/* Lの価格 */
	private Integer price_l;
	/* 画像パス */
	private String image_path;
	/* 削除パス */
	private Boolean deleted;
	/* トッピングリスト */
	private List<Topping> toppingList;
	
}
