package jp.co.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Item;

/**
 * 商品を操作する.
 * 
 * @author iidashuhei
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item>ITEM_ROW_MAPPER = (rs,i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
//		item.getToppingList(rs.getst)
		return item;
	};
	/**
	 * 商品一覧を表示する.
	 * 
	 * @return 商品一覧
	 */
	public List<Item> findAll(){
		String sql = "select id,name,description,price_m,price_l,image_path from items";
		SqlParameterSource param = new MapSqlParameterSource();
		return template.query(sql, param, ITEM_ROW_MAPPER);
	}
	/**
	 * 商品を曖昧検索する.
	 * 
	 * @param name 商品の名前
	 * @return 曖昧検索の結果
	 */
	public List<Item> findByName(String name){
		String sql = "select id,name,description,price_m,price_l,image_path from items where name like :name";
		SqlParameterSource param = new MapSqlParameterSource().addValue(name, '%' + name + '%');
		return template.query(sql, param, ITEM_ROW_MAPPER);
	}
	/**
	 * 商品詳細検索する.
	 * 
	 * @param id ID
	 * @return 商品1件の検索結果
	 */
	public Item load(Integer id) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path from items where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, ITEM_ROW_MAPPER);
	}
	
}
