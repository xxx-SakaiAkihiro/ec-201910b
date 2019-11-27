package jp.co.example.ecommerce_b.form;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.User;

/**
 * 注文する際に使用するフォーム.
 * 
 * @author iidashuhei
 *
 */
public class OrderForm {

	/* ID */
	private String id;
	/* UserId */
	private String userId;
	/* 状態 */
	private String status;
	/* 合計金額 */
	private String totalPrice;
	/* 注文日 */
	private Date orderDate;
	/* 宛先氏名 */
	private String destinationName;
	/* 宛先Eメール */
	private String destinationEmail;
	/* 宛先郵便番号 */
	private String destinationZipcode;
	/* 宛先住所 */
	private String destinationAddress;
	/* 宛先TEL */
	private String destinationTel;
	/* 配達時間 */
	private Timestamp deliveryTime;
	/* 支払方法 */
	private String paymentMethod;
	/* ユーザ */
	private User user;
	/* 注文したアイテム */
	private List<OrderItem> orderItemList;
	/* クレジットカード番号 */
	private Integer card_number;
	/* カード有効期限（年） */
	private Integer card_exp_year;
	/* カード有効期限（月） */
	private Integer card_exp_month;
	/* カード名義 */
	private String card_name;
	/* セキュリティコード */
	private Integer card_cvv;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the totalPrice
	 */
	public String getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the destinationName
	 */
	public String getDestinationName() {
		return destinationName;
	}
	/**
	 * @param destinationName the destinationName to set
	 */
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	/**
	 * @return the destinationEmail
	 */
	public String getDestinationEmail() {
		return destinationEmail;
	}
	/**
	 * @param destinationEmail the destinationEmail to set
	 */
	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}
	/**
	 * @return the destinationZipcode
	 */
	public String getDestinationZipcode() {
		return destinationZipcode;
	}
	/**
	 * @param destinationZipcode the destinationZipcode to set
	 */
	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}
	/**
	 * @return the destinationAddress
	 */
	public String getDestinationAddress() {
		return destinationAddress;
	}
	/**
	 * @param destinationAddress the destinationAddress to set
	 */
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	/**
	 * @return the destinationTel
	 */
	public String getDestinationTel() {
		return destinationTel;
	}
	/**
	 * @param destinationTel the destinationTel to set
	 */
	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}
	/**
	 * @return the deliveryTime
	 */
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * @param deliveryTime the deliveryTime to set
	 */
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the orderItemList
	 */
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	/**
	 * @param orderItemList the orderItemList to set
	 */
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	/**
	 * @return the card_number
	 */
	public Integer getCard_number() {
		return card_number;
	}
	/**
	 * @param card_number the card_number to set
	 */
	public void setCard_number(Integer card_number) {
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
		return "OrderForm [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
				+ ", paymentMethod=" + paymentMethod + ", user=" + user + ", orderItemList=" + orderItemList
				+ ", card_number=" + card_number + ", card_exp_year=" + card_exp_year + ", card_exp_month="
				+ card_exp_month + ", card_name=" + card_name + ", card_cvv=" + card_cvv + "]";
	}
	

	

	

}
