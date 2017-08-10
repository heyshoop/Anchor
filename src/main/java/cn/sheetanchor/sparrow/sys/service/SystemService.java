package cn.sheetanchor.sparrow.sys.service;

import cn.sheetanchor.common.persistence.Page;
import cn.sheetanchor.common.security.shiro.session.SessionDAO;
import cn.sheetanchor.sparrow.sys.model.Menu;
import cn.sheetanchor.sparrow.sys.model.Role;
import cn.sheetanchor.sparrow.sys.model.User;

import java.util.List;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2017/2/3
 * @Desc 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 */
public interface SystemService {

    /**
     * @Author 阁楼麻雀
     * @Date 2017/2/7 16:53
     * @Desc 校验用户名密码
     */
    User getUserByLoginName(String username);
    /**
     * @Author 阁楼麻雀
     * @Date 2017/2/7 16:56
     * @Desc 更新登录IP和时间
     */
    void updateUserLoginInfo(User user);
    /**
     * @Author 阁楼麻雀
     * @Date 2017/2/25 11:13
     * @Desc 获取用户信息
     */
    User getUser(String id);
    /**
     * @Author 阁楼麻雀
     * @Date 2017/2/25 11:31
     * @Desc 更新用户信息
     */
    void updateUserInfo(User currentUser);
    /**
     * @Author 阁楼麻雀
     * @Date 2017/2/25 17:22
     * @Desc 验证密码
     */
    boolean validatePassword(String oldPassword, String password);
    /**
     * @Author 阁楼麻雀
     * @Date 2017/2/25 17:26
     * @Desc 修改密码
     */
    void updatePasswordById(String id, String loginName, String newPassword);
    /**
     * @Author 阁楼麻雀
     * @Date 2017/3/7 14:37
     * @Desc 查询角色
     */
    List<Role> findAllRole();
    /**
     * @Author 阁楼麻雀
     * @Date 2017/3/7 14:47
     * @Desc 删除用户
     */
    void deleteUser(User user);

    /**
     * @Author 阁楼麻雀
     * @Date 2017/4/27 16:32
     * @Desc 获取sessionDao
     */
    SessionDAO getSessionDao();

    /**
     * @Author 阁楼麻雀
     * @Date 2017/4/28 14:18
     * @Desc 分页查询用户
     */
    Page<User> findUser(Page<User> sysUserPage, User user);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午8:41
     * @Des: 获取菜单
     */
    Menu getMenu(String id);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午8:43
     * @Desc: 获取所有菜单
     */
    List<Menu> findAllMenu();

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午8:44
     * @Desc: 保存菜单
     */
    void saveMenu(Menu menu);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午9:04
     * @Desc: 删除菜单
     */
    void deleteMenu(Menu menu);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午9:31
     * @Desc: 更新菜单分类
     */
    void updateMenuSort(Menu menu);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:45
     * @Desc: 无分页查询人员列表
     */
    List<User> findUser(User user);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:49
     * @Desc: 获取角色
     */
    Role getRole(String roleId);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:52
     * @Desc: 保存角色
     */
    void saveRole(Role role);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:58
     * @Desc: 删除角色
     */
    void deleteRole(Role role);

    /** 
     * @Author: 阁楼麻雀 
     * @Date: 2017/8/10 下午2:04
     * @Desc: 从角色中移除人员
     */
    Boolean outUserInRole(Role role, User user);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:15
     * @Desc: 分配用户角色
     */
    User assignUserToRole(Role role, User user);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:17
     * @Desc: 查询角色信息
     */
    Object getRoleByName(String name);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:18
     * @Desc: 查询角色信息
     */
    Object getRoleByEnname(String enname);


    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:49
     * @Desc: 保存用户信息
     */
    void saveUser(User user);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:59
     * @Desc: 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
     */
    List<User> findUserByOfficeId(String officeId);
}
