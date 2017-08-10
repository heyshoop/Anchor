package cn.sheetanchor.sparrow.sys.dao;

import cn.sheetanchor.common.persistence.TreeDao;
import cn.sheetanchor.common.persistence.annotation.MyBatisDao;
import cn.sheetanchor.sparrow.sys.model.Office;


/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2017/2/7
 * @Desc 部门Dao
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {

}
