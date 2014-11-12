package micro.core.dao.statement;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月12日 上午11:42:11
 */
public interface ArticleMapper {

	String INSERT_SQL = "INSERT INTO content (type, title, content, content_category_id, status, position, clicks, creator, created, updated) VALUES (:type, :title, :content, :content_category_id, :status, :position, :clicks, :creator, :created, :updated);";

	String DELETE_SQL = "DELETE FROM content WHERE content_id = :content_id;";
	
	String UPDATE_SQL = "UPDATE content SET type = :type, title = :title, content = :content, content_category_id = :content_category_id, status = :status, position = :position, updated = :updated WHERE content_id = :content_id;";
	
	String SELECT_CAT_SQL = "SELECT content_id, type, title, content, content_category_id, status, position, clicks, creator, created, updated FROM content WHERE content_category_id = :content_category_id";
	
	String SELECT_ONE_SQL = "SELECT content_id, type, title, content, content_category_id, status, position, clicks, creator, created, updated FROM content WHERE content_id = :content_id";
}
