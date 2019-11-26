package jp.co.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;



/**
 * トッピングを注文するリポジトリ.
 * 
 * @author iidashuhei
 *
 */
@Repository
public class OrderToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
//	private static final RowMapper<OrderTopping>ORDERTOPPING_ROW_MAPPING = (rs,i) -> {
//		OrderTopping orderTopping = new OrderTopping();
//		orderTopping.setId(rs.getInt("id"));
//		orderTopping.setToppingId(rs.getInt("topping_id"));
//		orderTopping.setOrderItemId(rs.getInt("order_item_id"));
//		return orderTopping;
//	};

	/**
	 * トッピングを挿入する.
	 * 
	 * @param orderTopping 注文したトッピング
	 */
	public void insert(Integer orderTopping) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		String sql = "insert into order_toppings(id,topping_id,order_item_id)values(:id,:toppingId,:orderItemId)";
		template.update(sql, param);
	}
}
