package micro.core.dao.statement;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月12日 上午11:42:11
 */
public interface ArticleMapper {

	String INSERT_SQL = "INSERT INTO content "
			+ "(type, title, content_category_id, status, "
			+ "position, clicks, creator, created, updated) VALUES "
			+ "(:type, :title, :content_category_id, :status, "
			+ ":position, :clicks, :creator, NOW(), NOW());";

	String DELETE_SQL = "DELETE FROM content WHERE content_id = :content_id;";
	
	String UPDATE_SQL = "UPDATE content SET type = :type, title = :title, "
			+ "content_category_id = :content_category_id, "
			+ "status = :status, position = :position, updated = :updated "
			+ "WHERE content_id = :content_id;";
	
	String QUERY_CAT_BY_CATID_SQL = "SELECT content_id, type, title, "
			+ "content_category_id, status, position, clicks, creator, "
			+ "created, updated FROM content WHERE "
			+ "content_category_id = :content_category_id "
			
			+ "AND type LIKE :type "
			+ "AND title LIKE :title "
			+ "AND status LIKE :status "
			
			+ " ORDER BY created DESC limit :start, :size";
	
	String QUERY_CAT_SQL = "SELECT content_id, type, title, "
			+ "content_category_id, status, position, clicks, creator, "
			+ "created, updated FROM content WHERE "
			
			+ "type LIKE :type "
			+ "AND title LIKE :title "
			+ "AND status LIKE :status "
			
			+ " ORDER BY created DESC limit :start, :size";
	
	String SELECT_ONE_SQL = "SELECT content_id, type, title, "
			+ "content_category_id, status, position, clicks, creator, "
			+ "created, updated FROM content WHERE content_id = :content_id";
	
	/**文章内容相关SQL*/
	String QUERY_CONTENT = "SELECT content FROM article WHERE id = :id;";
	
	String INSERT_CONTENT = "INSERT INTO article (id, content, gmt_created, gmt_updated) "
			+ "VALUES (:id, :content, NOW(), NOW());";
	
	String DELETE_CONTENT = "DELETE FROM article WHERE id = :id;";
	
	String UPDATE_CONTENT = "UPDATE article SET content = :content, gmt_updated = NOW() WHERE id = :id;";
	
	String COVER_MEDIA_INSERT = "INSERT INTO `ec_core`.`media` "
			+ "(`entity_type_id`, `url`, `created`, `updated`) "
			+ "VALUES (2, :url, NOW(), NOW());";
	
	String CONTENT_MEDIA_INSERT = "INSERT INTO `ec_core`.`content_media` "
			+ "(`content_id`, `media_id`, `is_cover`) VALUES "
			+ "(:content_id, :media_id, 1);";
	
	String Query_Cover = "SELECT url FROM media WHERE media_id in "
			+ "(SELECT media_id FROM content_media WHERE content_id = :content_id and is_cover = 1);";
	
	String UPDATE_COVER = "UPDATE media SET url = :url WHERE media_id IN "
			+ "(SELECT media_id FROM content_media WHERE content_id = :content_id and is_cover = 1);";
	
}
