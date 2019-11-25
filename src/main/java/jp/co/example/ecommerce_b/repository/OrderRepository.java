package jp.co.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Order;

/**
 * 注文するリポジトリ.
 * 
 * @author iidashuhei
 *
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs,i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("totalPrice"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		return order;
	};
	
	/**
	 * 注文を挿入する.
	 * 
	 * @param order 注文した商品
	 */
	public void insertOrder(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "insert into orders(id,user_id,status,total_price,order_date,"
				+ "destination_name,destination_email,destination_zipcode,"
				+ "destination_address,destination_tel,delivery_time,payment_method)";
		template.update(sql, param);
	}
	/**
	 * 注文された商品を更新する.
	 * 
	 * @param order 
	 */
	public void update(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "select id,userId,status,totalPrice,orderDate,destinationName,"
				+ "destinationEmail,destinationZipcode,destinationAddress,destinationTel,"
				+ "deliveryTime,paymentMethod,user,orderItemList where order =:order";
		template.update(sql, param);
	}
	/**
	 * 商品を1件検索してくる.
	 * 
	 * @param orderId OrderId
	 * @return 商品1件の情報
	 */
	public Order load(Integer orderId) {
		String sql = "select id,userId,status,totalPrice,orderDate,destinationName,"
				+ "destinationEmail,destinationZipcode,destinationAddress,destinationTel,"
				+ "deliveryTime,paymentMethod,user,orderItemList where order_id =:orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("order_id",orderId);
		return template.queryForObject(sql, param,ORDER_ROW_MAPPER);
	}
}
