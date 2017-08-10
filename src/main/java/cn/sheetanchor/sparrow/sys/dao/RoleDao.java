package cn.sheetanchor.sparrow.sys.dao;

import cn.sheetanchor.common.persistence.BaseDao;
import cn.sheetanchor.common.persistence.annotation.MyBatisDao;
import cn.sheetanchor.sparrow.sys.model.Role;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2017/2/6
 * @Desc 角色dao
 */
@MyBatisDao
public interface RoleDao extends BaseDao<Role> {

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:55
     * @Desc: 删除角色与菜单关系权限
     */
    void deleteRoleMenu(Role role);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:56
     * @Desc: 新增角色与菜单关系权限
     */
    void insertRoleMenu(Role role);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:56
     * @Desc: 删除角色与部门关联
     */
    void deleteRoleOffice(Role role);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:57
     * @Desc: 新增部门与角色关联
     */
    void insertRoleOffice(Role role);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:22
     * @Desc: 查询角色信息
     */
    Role getByName(Role r);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:23
     * @Desc: 查询角色信息
     */
    Role getByEnname(Role r);
}
