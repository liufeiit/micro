package micro.core.dao.statement;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月12日 下午11:24:23
 */
public interface ArticleCategoryMapper {

	String SELECT_ALL = "SELECT content_category_id, name, description, icon, position, created, updated FROM content_category;";
	
	String UPDATE_SQL = "UPDATE content_category SET name = :name, description = :description, icon = :icon, position = :position, updated = :updated WHERE content_category_id = :content_category_id;";
	
	String INSERT_SQL = "INSERT INTO content_category(content_category_id, name, description, icon, position, created, updated) VALUES (:content_category_id, :name, :description, :icon, :position, NOW(), NOW());";
	
	String DELETE_SQL = "DELETE FROM content_category WHERE content_category_id = :content_category_id;";
}
