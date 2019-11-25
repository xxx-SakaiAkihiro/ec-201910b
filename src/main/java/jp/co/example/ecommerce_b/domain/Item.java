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
	private String imagePath;
	/* 削除パス */
	private Boolean deleted;
	/* トッピングリスト */
	private List<Topping> toppingList;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", priceM=" + priceM + ", priceL="
				+ priceL + ", imagePath=" + imagePath + ", deleted=" + deleted + ", toppingList=" + toppingList + "]";
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
	 * @return the priceM
	 */
	public Integer getPriceM() {
		return priceM;
	}

	/**
	 * @param priceM the priceM to set
	 */
	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	/**
	 * @return the priceL
	 */
	public Integer getPriceL() {
		return priceL;
	}

	/**
	 * @param priceL the priceL to set
	 */
	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

	/**
	 * @return the image_path
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param image_path the image_path to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
