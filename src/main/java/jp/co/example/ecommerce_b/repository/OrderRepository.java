package jp.co.example.ecommerce_b.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.Order;
import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.OrderTopping;
import jp.co.example.ecommerce_b.domain.Topping;

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

	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;

		int preOrderId = 0;
		int preOrderItemId = 0;
		while (rs.next()) {
			int nowOrderId = rs.getInt("o_id");
			if (preOrderId != nowOrderId) {
				Order order = new Order();
				order.setId(nowOrderId);
				order.setUserId(rs.getInt("o_user_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipcode(rs.getString("o_destination_zipcode"));
				order.setDestinationAddress(rs.getString("o_destination_address"));
				order.setDestinationTel(rs.getString("o_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
				order.setPaymentMethod(rs.getInt("o_payment_method"));
				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
			}

			int nowOrderItemId = rs.getInt("oi_id");
			if (preOrderItemId != nowOrderItemId) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(nowOrderItemId);
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				System.out.println("Extractor:" + rs.getString("i_name") + ":" + rs.getString("oi_size"));
				orderItem.setSize((rs.getString("oi_size")).charAt(0));
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);

				Item item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));
				item.setDeleted(rs.getBoolean("i_deleted"));
				orderItem.setItem(item);
			}

			if (rs.getInt("ot_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
				orderToppingList.add(orderTopping);

				Topping topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPriceM(rs.getInt("t_price_m"));
				topping.setPriceL(rs.getInt("t_price_l"));
				orderTopping.setTopping(topping);
			}
			preOrderId = nowOrderId;
			preOrderItemId = nowOrderItemId;
		}
		return orderList;
	};
	
	private SimpleJdbcInsert insert;
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert =
		new SimpleJdbcInsert((JdbcTemplate)template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}

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
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		Number key = insert.executeAndReturnKey(param);
		order.setId(key.intValue());
		return order;
	}

	/**
	 * 注文された商品を更新する.
	 * 
	 * @param order
	 */
	
	public void orderUpdate(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql =" update orders set destination_name=:destinationName,"
				+ "destination_email=:destinationEmail,"
				+ "destination_zipcode=:destinationZipcode,"
				+ "destination_address=:destinationAddress,"
				+ "destination_tel=:destinationTel,delivery_time=:deliveryTime,"
				+ "payment_method=:paymentMethod,status=:status,order_date=:orderDate,total_price=:totalPrice "
				+ " where id = :id;";
		
		template.update(sql, param);
	}
	
	
	public void update(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "update orders user_id=:userId,status=:status,"
				+ "total_price=:totalPrice,order_date=:orderDate,destination_name=:destinationName,"
				+ "destination_email=:destinationEmail,destination_zipcode=:destinationZipcode,"
				+ "destination_address=:destinationAddress,destination_tel=:destinationTel,"
				+ "delivery_time=:deliveryTime,payment_method=:paymentMethod where id = :id";
		template.update(sql, param);
	}
	/**
	 * 注文情報のユーザーIDを更新する
	 * 
	 * @param order 注文情報
	 */
	public void updateUserId(Integer loginUserId,Integer sessionUserId) {
		String sql = "UPDATE orders set user_id = :loginUserId WHERE user_id = :sessionUserId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("sessionUserId", sessionUserId).addValue("loginUserId", loginUserId);
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
	 * ユーザーIDとステータスから注文情報を取得.
	 * 
	 * @param userId ユーザーID
	 * @param status 状態
	 * @return 
	 */
	public List<Order> findByUserIdAndStatus(Integer userId,Integer status) {
		String sql = "SELECT o.id o_id,o.user_id o_user_id,o.status o_status,o.total_price o_total_price,o.order_date o_order_date,o.destination_name o_destination_name,o.destination_email o_destination_email,o.destination_zipcode o_destination_zipcode,o.destination_address o_destination_address,o.destination_tel o_destination_tel,o.delivery_time o_delivery_time,o.payment_method o_payment_method\r\n" + 
				",oi.id oi_id,oi.item_id oi_item_id,oi.order_id oi_order_id,oi.quantity oi_quantity,oi.size oi_size\r\n" + 
				",i.id i_id,i.name i_name,i.description i_description,i.price_m i_price_m,i.price_l i_price_l,i.image_path i_image_path,i.deleted i_deleted\r\n" + 
				",ot.id ot_id,ot.topping_id ot_topping_id,ot.order_item_id ot_order_item_id\r\n" + 
				",t.id t_id,t.name t_name,t.price_m t_price_m,t.price_l t_price_l\r\n" + 
				"FROM orders o\r\n" + 
				"LEFT JOIN order_Items oi\r\n" + 
				"ON o.id=oi.order_id\r\n" + 
				"LEFT JOIN items i\r\n" + 
				"ON oi.item_id=i.id\r\n" + 
				"LEFT JOIN order_toppings ot\r\n" + 
				"ON oi.id=ot.order_item_id\r\n" + 
				"LEFT JOIN toppings t\r\n" + 
				"ON ot.topping_id=t.id\r\n" + 
				"WHERE o.user_id =:userId AND o.status = :status";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId).addValue("status", status);
		List<Order> orderList = template.query(sql, param,ORDER_RESULT_SET_EXTRACTOR);
		return orderList;
	}
	
	/**
	 * ユーザーのショッピングカート内の件数を取得する
	 * @param userId ユーザーID
	 * @return 件数
	 */
	public Integer countInCart(Integer userId) {
		String sql = "SELECT count(*) FROM order_items AS i JOIN orders AS o ON i.order_id = o.id WHERE o.user_id = :userId AND o.status = 0";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		Integer count = template.queryForObject(sql, param, Integer.class);
		return count;
	}
	
	public void deleteById(Integer temporalOrderId) {
		String sql = "DELETE FROM orders WHERE id = :temporalOrderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("temporalOrderId", temporalOrderId);
		template.update(sql, param);
	}
	
}
