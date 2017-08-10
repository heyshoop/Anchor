package cn.sheetanchor.sparrow.sys.dao;

import cn.sheetanchor.common.persistence.BaseDao;
import cn.sheetanchor.common.persistence.annotation.MyBatisDao;
import cn.sheetanchor.sparrow.sys.model.Menu;

import java.util.List;


/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2017/2/6
 * @Desc 菜单Dao
 */
@MyBatisDao
public interface MenuDao extends BaseDao<Menu> {

    /**
     * @Author 阁楼麻雀
     * @Date 2017/4/27 16:12
     * @Desc 通过用户ID查询菜单
     */
    public List<Menu> findByUserId(Menu menu);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午8:48
     * @Desc: 根据父节点查找子节点
     */
    List<Menu> findByParentIdsLike(Menu m);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午9:04
     * @Desc: 更新父节点
     */
    void updateParentIds(Menu e);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午9:34
     * @Desc: 更新分类
     */
    void updateSort(Menu menu);
}
