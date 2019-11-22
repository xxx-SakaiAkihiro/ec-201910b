package jp.co.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Item;

@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item>ITEM_ROW_MAPPER = (rs,i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice_m(rs.getInt("priceM"));
		item.setPrice_l(rs.getInt("priceL"));
		item.setImage_path(rs.getString("imagePath"));
//		item.getToppingList(rs.getst)
		return item;
	};
	public List<Item> findAll(){
		String sql = "select id,name,description,price_m,price_l,image_path from items";
		SqlParameterSource param = new MapSqlParameterSource();
		return template.query(sql, param, ITEM_ROW_MAPPER);
	}
}
