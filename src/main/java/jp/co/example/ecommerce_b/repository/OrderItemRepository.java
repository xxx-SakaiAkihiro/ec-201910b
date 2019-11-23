package jp.co.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
	public NamedParameterJdbcTemplate template;
	
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
}
