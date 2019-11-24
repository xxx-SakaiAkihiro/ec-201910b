package jp.co.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
}
