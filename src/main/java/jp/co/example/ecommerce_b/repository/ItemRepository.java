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
 * 商品を操作するリポジトリ.
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
		return item;
	};
	/**
	 * 商品一覧を表示する.
	 * 
	 * @return 商品一覧
	 */
	public List<Item> findAll(){
		String sql = "select id,name,description,price_m,price_l,image_path from items order by price_m";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	/**
	 * 商品を曖昧検索、値段順で並び替える.
	 * 
	 * @param name 商品の名前
	 * @return 曖昧検索と値段順の結果
	 */
	public List<Item> findByNameAndSort(String searchName, String sort){
		
		//条件式を指定するsql文
		String whereSql = "";
		if(!searchName.equals("")) {
			whereSql = " where name Ilike :searchName ";
		} else {
			whereSql = "";
		}
		//並び替えを指定するsql文
		String orderSql = " order by price_m ";
		if("expensive".equals(sort)) {
			orderSql += "desc ";
		} else {
			orderSql += "asc ";
		}
		//sqlを発行
		String sql = "select id,name,description,price_m,price_l,image_path from items" + whereSql + orderSql;
		SqlParameterSource param = new MapSqlParameterSource().addValue("searchName", '%' + searchName + '%');
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
	/**
	 * 商品一覧の総件数を検索.
	 * 
	 * @return 商品一覧の総件数
	 */
	public Integer count() {
		String sql = "select count(*) from items";
		SqlParameterSource param = new MapSqlParameterSource();
		return template.queryForObject(sql, param,Integer.class);
	}
	/**
	 * 検索された商品一覧の件数を取得.
	 * 
	 * @return 検索された商品一覧の件数、検索されなければ全件件数
	 */
	public Integer orderedItemcount(String searchName) {
		//条件式
		String whereSql = "";
		if(searchName != null) {
			whereSql = " where name Ilike :searchName";
		} else {
			whereSql = "";
		}
		String sql = "select count(*) from items" + whereSql;
		SqlParameterSource param = new MapSqlParameterSource().addValue("searchName", searchName);
		return template.queryForObject(sql, param,Integer.class);
	}
	
	/**
	 * 値段が高い商品順で検索する.
	 * 
	 * @return 値段が高い商品順
	 */
	public List<Item> findByExpensive(){
		String sql = "select id,name,description,price_m,price_l,image_path from items order by price_m desc";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	/**
	 * 値段が安い商品順で検索する.
	 * 
	 * @return 値段が安い商品順
	 */
	public List<Item> findByCheap(){
		String sql = "select id,name,description,price_m,price_l,image_path from items order by price_m";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
}
