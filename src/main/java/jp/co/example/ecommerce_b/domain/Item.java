package jp.co.example.ecommerce_b.domain;

import java.util.List;

public class Item {

	/* ID */
	private Integer id;
	/* 名前 */
	private String name;
	/* 説明 */
	private String description;
	/* Mの価格 */
	private Integer priceM;
	/* Lの価格 */
	private Integer priceL;
	/* 画像パス */
	private String image_path;
	/* 削除パス */
	private Boolean deleted;
	/* トッピングリスト */
	private List<Topping> toppingList;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price_m=" + priceM
				+ ", price_l=" + priceL + ", image_path=" + image_path + ", deleted=" + deleted + ", toppingList="
				+ toppingList + "]";
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price_m
	 */
	public Integer getPrice_m() {
		return priceM;
	}

	/**
	 * @param price_m the price_m to set
	 */
	public void setPrice_m(Integer price_m) {
		this.priceM = price_m;
	}

	/**
	 * @return the price_l
	 */
	public Integer getPrice_l() {
		return priceL;
	}

	/**
	 * @param price_l the price_l to set
	 */
	public void setPrice_l(Integer price_l) {
		this.priceL = price_l;
	}

	/**
	 * @return the image_path
	 */
	public String getImage_path() {
		return image_path;
	}

	/**
	 * @param image_path the image_path to set
	 */
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	/**
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the toppingList
	 */
	public List<Topping> getToppingList() {
		return toppingList;
	}

	/**
	 * @param toppingList the toppingList to set
	 */
	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}

}
