package jp.co.example.ecommerce_b.domain;

public class Topping {

	/* ID */
	private Integer id;
	/* 名前 */
	private String name;
	/* Mの価格 */
	private Integer priceM;
	/* Lの価格 */
	private Integer priceL;
	
	@Override
	public String toString() {
		return "topping [id=" + id + ", name=" + name + ", priceM=" + priceM + ", priceL=" + priceL + "]";
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
	
	
}
