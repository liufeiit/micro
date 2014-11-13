package micro.core.dataobject;

import tulip.data.annotation.Column;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月12日 上午11:42:46
 */
public class ArticleDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "content_id")
	private long id;

	@Column(name = "type")
	private String type;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "content_category_id")
	private long catId;

	@Column(name = "status")
	private String status;

	@Column(name = "position")
	private int position = 0;

	@Column(name = "clicks")
	private long clicks = 0L;

	@Column(name = "creator")
	private long creator;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the catId
	 */
	public long getCatId() {
		return catId;
	}

	/**
	 * @param catId
	 *            the catId to set
	 */
	public void setCatId(long catId) {
		this.catId = catId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the clicks
	 */
	public long getClicks() {
		return clicks;
	}

	/**
	 * @param clicks
	 *            the clicks to set
	 */
	public void setClicks(long clicks) {
		this.clicks = clicks;
	}

	/**
	 * @return the creator
	 */
	public long getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(long creator) {
		this.creator = creator;
	}
}