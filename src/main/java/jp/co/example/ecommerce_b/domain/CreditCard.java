package jp.co.example.ecommerce_b.domain;

/**
 * クレジットカード情報で使用するドメイン.
 * 
 * @author riho.ikeda
 *
 */
public class CreditCard {
	/* クレジットカード番号 */
	private long card_number;
	/* カード有効期限（年） */
	private Integer card_exp_year;
	/* カード有効期限（月） */
	private Integer card_exp_month;
	/* カード名義 */
	private String card_name;
	/* セキュリティコード */
	private Integer card_cvv;
	/**
	 * @return the card_number
	 */
	public long getCard_number() {
		return card_number;
	}
	/**
	 * @param card_number the card_number to set
	 */
	public void setCard_number(long card_number) {
		this.card_number = card_number;
	}
	/**
	 * @return the card_exp_year
	 */
	public Integer getCard_exp_year() {
		return card_exp_year;
	}
	/**
	 * @param card_exp_year the card_exp_year to set
	 */
	public void setCard_exp_year(Integer card_exp_year) {
		this.card_exp_year = card_exp_year;
	}
	/**
	 * @return the card_exp_month
	 */
	public Integer getCard_exp_month() {
		return card_exp_month;
	}
	/**
	 * @param card_exp_month the card_exp_month to set
	 */
	public void setCard_exp_month(Integer card_exp_month) {
		this.card_exp_month = card_exp_month;
	}
	/**
	 * @return the card_name
	 */
	public String getCard_name() {
		return card_name;
	}
	/**
	 * @param card_name the card_name to set
	 */
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	/**
	 * @return the card_cvv
	 */
	public Integer getCard_cvv() {
		return card_cvv;
	}
	/**
	 * @param card_cvv the card_cvv to set
	 */
	public void setCard_cvv(Integer card_cvv) {
		this.card_cvv = card_cvv;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreditCard [card_number=" + card_number + ", card_exp_year=" + card_exp_year + ", card_exp_month="
				+ card_exp_month + ", card_name=" + card_name + ", card_cvv=" + card_cvv + "]";
	}
	
	

}
