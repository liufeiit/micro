package me.andpay.ti.daf.dao;

/**
 * 数据库顺序号Dao接口定义类。
 * 
 * @author sea.bao
 */
public interface DbSequenceDao {
	/**
	 * 返回数据库下一个顺序号。
	 * @param sequenceId
	 * @return
	 */
	long nextValue(String sequenceId);
}
