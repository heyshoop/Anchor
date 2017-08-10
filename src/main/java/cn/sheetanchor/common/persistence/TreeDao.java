package cn.sheetanchor.common.persistence;

import java.util.List;
/**
 * @Author 阁楼麻雀
 * @Date 2017/4/27 14:53
 * @Desc DAO支持类实现
 */

public interface TreeDao<T extends TreeEntity<T>> extends BaseDao<T> {

	/**
	 * 找到所有子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 更新所有父节点字段
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
	
}