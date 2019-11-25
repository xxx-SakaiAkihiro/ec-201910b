
package jp.co.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import jp.co.example.ecommerce_b.domain.Topping;

/**
 * toppingsテーブルを操作するリポジトリ.
 * 
 * @author riho.ikeda
 *
 */
public class ToppingRepository {
	/**
	 * Toppingオブジェクトを生成するローマッパー.
	 */
	public static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("pricegetint_m"));
		topping.setPriceL(rs.getInt("pricegetint_l"));

		return topping;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 *トッピング情報を取得.
	 * 
	 * @return トッピング情報
	 */
	public List<Topping> findAll() {
		String sql = "SELECT id,name,pricegetint_m,pricegetint_l FROM toppings  ORDER BY id ;";
		List<Topping> toppingtList = template.query(sql, TOPPING_ROW_MAPPER);
		return toppingtList;

	}

}
