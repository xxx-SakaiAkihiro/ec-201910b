package jp.co.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.OrderItem;

/**
 * 商品を注文するリポジトリ.
 * 
 * @author iidashuhei
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 商品を挿入する.
	 * 
	 * @param orderItem 注文された商品
	 */
	public void insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		String sql = "insert into order_items(id,item_id,order_id,quantity,size)values(:id,:itemId,:orderId,:quantity,:size)";
		template.update(sql, param);
	}
	
	/**
	 * 商品とトッピングを削除.
	 * 
	 * @param id ID
	 */
	public void deleteById(Integer id) {
		String sql = "WITH deleted AS (DELETE FROM order_items WHERE id=:id RETURNING id) DELETE FROM order_toppings WHERE order_item_id IN (SELECT order_item_id FROM deleted);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
}
