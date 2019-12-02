package jp.co.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.Want;



@Repository
public class WantsRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs,i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		return item;
	};
	
	private static final RowMapper<Want> WANT_ROW_MAPPER = (rs,i) -> {
		Want want = new Want();
		want.setId(rs.getInt("id"));
		want.setUserId(rs.getInt("user_id"));
		want.setItemId(rs.getInt("item_id"));
		return want;
	};
	
	public void insert(Integer userId,Integer itemId) {
		System.out.println("れぽじとりItemId" + itemId);
		String sql = "INSERT INTO wants (user_id,item_id) VALUES (:userId,:itemId)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId).addValue("itemId", itemId);
		template.update(sql, param);
	}
	public void delete(Integer userId,Integer itemId) {
		String sql = "DELETE FROM wants WHERE user_id = :userId AND item_id = :itemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId).addValue("itemId", itemId);
		template.update(sql, param);
	}
	public List<Item> findByUserId(Integer userId){
		String sql = "SELECT i.id AS id,i.name AS name ,i.description AS description,i.price_m AS price_m,i.price_l AS price_l,i.image_path AS image_path,i.deleted AS deleted FROM items AS i JOIN wants AS w ON i.id = w.item_id WHERE w.user_id = :userId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		return template.query(sql, param,ITEM_ROW_MAPPER);
	}
	
	public List<Want> findByUserIdAndItemId(Integer userId,Integer itemId) {
		String sql = "SELECT id,user_id,item_id FROM wants WHERE user_id = :userId AND item_id = :itemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("itemId", itemId);
		List<Want> wantList = template.query(sql, param,WANT_ROW_MAPPER);
		return wantList;
	}
	

}

