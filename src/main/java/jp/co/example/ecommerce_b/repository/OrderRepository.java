package jp.co.example.ecommerce_b.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
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
	
//	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
//		List<Order> orderList = new ArrayList<>();
//
//	};

	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
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
	public void insert(Order order) {
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
		String sql = "update orders set id=:id,user_id=:userId,status=:status,"
				+ "total_price=:totalPrice,order_date=:orderDate,destination_name=:destinationName,"
				+ "destination_email=:destinationEmail,destination_zipcode=:destinationZipcode,"
				+ "destination_address=:destinationAddress,destination_tel=:destinationTel,"
				+ "delivery_time=:deliveryTime,payment_method=:paymentMethod";
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
		SqlParameterSource param = new MapSqlParameterSource().addValue("order_id", orderId);
		return template.queryForObject(sql, param, ORDER_ROW_MAPPER);
	}
	/**
	 * 
	 * 
	 * @param userId UserId
	 * @param status 状態
	 * @return
	 */
//	public Order findByUserIdAndStatus(Integer userId,Integer status) {
//		String sql = "";
//		SqlParameterSource param = new MapSqlParameterSource().addValue(, );
//		return template.queryForObject(sql, param, );
//	}
}
