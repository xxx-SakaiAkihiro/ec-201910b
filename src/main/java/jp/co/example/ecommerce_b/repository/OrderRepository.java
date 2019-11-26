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
			int nowOrderId = rs.getInt("orders.id");
			if (preOrderId != nowOrderId) {
				Order order = new Order();
				order.setId(nowOrderId);
				order.setUserId(rs.getInt("orders.user_id"));
				order.setStatus(rs.getInt("orders.status"));
				order.setTotalPrice(rs.getInt("orders.total_price"));
				order.setOrderDate(rs.getDate("orders.order_date"));
				order.setDestinationName(rs.getString("orders.destination_name"));
				order.setDestinationEmail(rs.getString("orders.destination_email"));
				order.setDestinationZipcode(rs.getString("orders.destination_zipcode"));
				order.setDestinationAddress(rs.getString("orders.destination_address"));
				order.setDestinationTel(rs.getString("orders.destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("orders.delivery_time"));
				order.setPaymentMethod(rs.getInt("orders.payment_method"));
				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
			}

			int nowOrderItemId = rs.getInt("order_Items.id");
			if (preOrderItemId != nowOrderItemId) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(nowOrderItemId);
				orderItem.setItemId(rs.getInt("order_Items.item_id"));
				orderItem.setOrderId(rs.getInt("order_Items.order_id"));
				orderItem.setQuantity(rs.getInt("order_Items.quantity"));
				orderItem.setSize((rs.getString("order_Items.size")).charAt(0));
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);

				Item item = new Item();
				item.setId(rs.getInt("items.id"));
				item.setName(rs.getString("items.name"));
				item.setDescription(rs.getString("items.description"));
				item.setPriceM(rs.getInt("items.price_m"));
				item.setPriceL(rs.getInt("items.price_l"));
				item.setImagePath(rs.getString("items.image_path"));
				item.setDeleted(rs.getBoolean("items.deleted"));
				orderItem.setItem(item);
			}

			if (rs.getInt("order_toppings.id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("order_toppings.id"));
				orderTopping.setToppingId(rs.getInt("order_toppings.topping_id"));
				orderTopping.setOrderItemId(rs.getInt("order_toppings.order_item_id"));
				orderToppingList.add(orderTopping);

				Topping topping = new Topping();
				topping.setId(rs.getInt("toppings.id"));
				topping.setName(rs.getString("toppings.name"));
				topping.setPriceM(rs.getInt("toppings.price_m"));
				topping.setPriceL(rs.getInt("toppings.price_l"));
				orderTopping.setTopping(topping);
			}
			preOrderId = nowOrderId;
			preOrderItemId = nowOrderItemId;
		}
		return orderList;
	};

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
	 * ユーザーIDとステータスから注文情報を取得
	 * 
	 * @param userId ユーザーId
	 * @param status 状態
	 * @return
	 */
	public Order findByUserIdAndStatus(Integer userId,Integer status) {
		String sql = "select id,user_id,status,total_price,order_date,destination_name,"
				+ "destination_email,destination_zipcode,destination_address,destination_tel,"
				+ "delivery_time,payment_method where user_id =:userId AND status = :status";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId).addValue("status", status);
		List<Order> orderList = template.query(sql, param,ORDER_ROW_MAPPER);
		if (orderList.size() == 0) {
			return null;
		}
		return orderList.get(0);
	}
}
