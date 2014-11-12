package micro.core.dataobject;

import java.io.Serializable;
import java.util.Date;

import tulip.data.annotation.Column;

/**
 * 基础数据结构.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月11日 上午11:01:11
 */
public class BaseDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "created")
	private Date created;
	@Column(name = "updated")
	private Date updated;

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *            the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}