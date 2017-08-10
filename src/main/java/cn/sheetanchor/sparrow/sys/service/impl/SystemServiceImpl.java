package cn.sheetanchor.sparrow.sys.service.impl;

import cn.sheetanchor.common.config.Global;
import cn.sheetanchor.common.persistence.Page;
import cn.sheetanchor.common.security.SystemAuthorizingRealm;
import cn.sheetanchor.common.security.shiro.session.SessionDAO;
import cn.sheetanchor.common.service.BaseService;
import cn.sheetanchor.common.service.ServiceException;
import cn.sheetanchor.common.utils.*;
import cn.sheetanchor.sparrow.sys.dao.*;
import cn.sheetanchor.sparrow.sys.model.Menu;
import cn.sheetanchor.sparrow.sys.model.Office;
import cn.sheetanchor.sparrow.sys.model.Role;
import cn.sheetanchor.sparrow.sys.model.User;
import cn.sheetanchor.sparrow.sys.service.SystemService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static cn.sheetanchor.common.utils.UserUtils.entryptPassword;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2017/2/7
 * @Desc
 */
@Service
@Transactional(readOnly = true)
public class SystemServiceImpl extends BaseService implements SystemService,InitializingBean {


    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private SessionDAO sessionDao;
    @Autowired
    private SystemAuthorizingRealm systemRealm;

    public SessionDAO getSessionDao() {
        return sessionDao;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO 同步activiti数据
    }
    /**
     * 根据登录名获取用户
     * @param loginName
     * @return User
     */
    public User getUserByLoginName(String loginName) {
        return UserUtils.getByLoginName(loginName);
    }

    @Transactional(readOnly = false)
    public void updateUserLoginInfo(User user) {
        // 保存上次登录信息
        user.setOldLoginIp(user.getLoginIp());
        user.setOldLoginDate(user.getLoginDate());
        // 更新本次登录信息
        user.setLoginIp(StringUtils.getRemoteAddr(Servlets.getRequest()));
        user.setLoginDate(new Date());
        userDao.updateLoginInfo(user);
    }

    public User getUser(String id) {
        return UserUtils.get(id);
    }

    @Transactional(readOnly = false)
    public void updateUserInfo(User user) {
        user.preUpdate();
        userDao.updateUserInfo(user);
        // 清除用户缓存
        UserUtils.clearCache(user);
    }

    public boolean validatePassword(String oldPassword, String password) {
        String plain = Encodes.unescapeHtml(oldPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0,16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }

    @Transactional(readOnly = false)
    public void updatePasswordById(String id, String loginName, String newPassword) {
        User user = new User(id);
        user.setPassword(entryptPassword(newPassword));
        userDao.updatePasswordById(user);
        // 清除用户缓存
        user.setLoginName(loginName);
        UserUtils.clearCache(user);
    }

    /**
     * @Author 阁楼麻雀
     * @Date 2017/4/28 14:21
     * @Desc 查询所有权限
     */
    public List<Role> findAllRole() {
        return UserUtils.getRoleList();
    }

    /**
     * @Author 阁楼麻雀
     * @Date 2017/4/28 14:20
     * @Desc 删除用户
     */
    public void deleteUser(User user) {
        userDao.delete(user);
        UserUtils.clearCache(user);
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2017/4/28 14:20
     * @Desc 分页查询用户列表
     */
    public Page<User> findUser(Page<User> page, User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        // 设置分页参数
        user.setPage(page);
        // 执行分页查询
        page.setList(userDao.findList(user));
        return page;
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午8:42
     * @Desc: 获取菜单
     */
    public Menu getMenu(String id) {
        return menuDao.get(id);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午8:43
     * @Desc: 获取所有菜单
     */
    public List<Menu> findAllMenu() {
        return UserUtils.getMenuList();
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午8:44
     * @Desc: 保存菜单
     */
    @Transactional(readOnly = false)
    public void saveMenu(Menu menu) {
        // 获取父节点实体
        menu.setParent(this.getMenu(menu.getParent().getId()));
        // 获取修改前的parentIds，用于更新子节点的parentIds
        String oldParentIds = menu.getParentIds();
        // 设置新的父节点串
        menu.setParentIds(menu.getParent().getParentIds()+menu.getParent().getId()+",");
        // 保存或更新实体
        if (StringUtils.isBlank(menu.getId())){
            menu.preInsert();
            menuDao.insert(menu);
        }else{
            menu.preUpdate();
            menuDao.update(menu);
        }

        // 更新子节点 parentIds
        Menu m = new Menu();
        m.setParentIds("%,"+menu.getId()+",%");
        List<Menu> list = menuDao.findByParentIdsLike(m);
        for (Menu e : list){
            e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
            menuDao.updateParentIds(e);
        }
        // 清除用户菜单缓存
        UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		// 清除权限缓存
		//systemRealm.clearAllCachedAuthorizationInfo();
        // 清除日志相关缓存
        CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午9:32
     * @Desc: 删除菜单
     */
    @Transactional(readOnly = false)
    public void deleteMenu(Menu menu) {
        menuDao.delete(menu);
        // 清除用户菜单缓存
        UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		// 清除权限缓存
		//systemRealm.clearAllCachedAuthorizationInfo();
        // 清除日志相关缓存
        CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午9:33
     * @Desc: 更新菜单分类
     */
    @Transactional(readOnly = false)
    public void updateMenuSort(Menu menu) {
        menuDao.updateSort(menu);
        // 清除用户菜单缓存
        UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		// 清除权限缓存
		//systemRealm.clearAllCachedAuthorizationInfo();
        // 清除日志相关缓存
        CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:46
     * @Desc: 无分页查询人员列表
     */
    public List<User> findUser(User user){
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        List<User> list = userDao.findList(user);
        return list;
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:50
     * @Desc: 获取角色
     */
    public Role getRole(String id) {
        return roleDao.get(id);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:53
     * @Desc: 保存角色
     */
    @Transactional(readOnly = false)
    public void saveRole(Role role) {
        if (StringUtils.isBlank(role.getId())){
            role.preInsert();
            roleDao.insert(role);
        }else{
            role.preUpdate();
            roleDao.update(role);
        }
        // 更新角色与菜单关联
        roleDao.deleteRoleMenu(role);
        if (role.getMenuList().size() > 0){
            roleDao.insertRoleMenu(role);
        }
        // 更新角色与部门关联
        roleDao.deleteRoleOffice(role);
        if (role.getOfficeList().size() > 0){
            roleDao.insertRoleOffice(role);
        }
        // 清除用户角色缓存
        UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:59
     * @Desc: 删除角色
     */
    @Transactional(readOnly = false)
    public void deleteRole(Role role) {
        roleDao.delete(role);
        // 清除用户角色缓存
        UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:07
     * @Desc: 从角色中移除人员
     */
    @Transactional(readOnly = false)
    public Boolean outUserInRole(Role role, User user) {
        List<Role> roles = user.getRoleList();
        for (Role e : roles){
            if (e.getId().equals(role.getId())){
                roles.remove(e);
                saveUser(user);
                return true;
            }
        }
        return false;
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:19
     * @Desc: 为用户分配角色
     */
    @Transactional(readOnly = false)
    public User assignUserToRole(Role role, User user) {
        if (user == null){
            return null;
        }
        List<String> roleIds = user.getRoleIdList();
        if (roleIds.contains(role.getId())) {
            return null;
        }
        user.getRoleList().add(role);
        saveUser(user);
        return user;
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:22
     * @Desc: 查询角色信息
     */
    public Role getRoleByName(String name) {
        Role r = new Role();
        r.setName(name);
        return roleDao.getByName(r);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:22
     * @Desc: 查询角色信息
     */
    public Role getRoleByEnname(String enname) {
        Role r = new Role();
        r.setEnname(enname);
        return roleDao.getByEnname(r);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:10
     * @Desc: 保存用户信息
     */
    @Transactional(readOnly = false)
    public void saveUser(User user) {
        if (StringUtils.isBlank(user.getId())){
            user.preInsert();
            userDao.insert(user);
        }else{
            // 清除原用户机构用户缓存
            User oldUser = userDao.get(user.getId());
            if (oldUser.getOffice() != null && oldUser.getOffice().getId() != null){
                CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + oldUser.getOffice().getId());
            }
            // 更新用户数据
            user.preUpdate();
            userDao.update(user);
        }
        if (StringUtils.isNotBlank(user.getId())){
            // 更新用户与角色关联
            userDao.deleteUserRole(user);
            if (user.getRoleList() != null && user.getRoleList().size() > 0){
                userDao.insertUserRole(user);
            }else{
                throw new ServiceException(user.getLoginName() + "没有设置角色！");
            }
            // 清除用户缓存
            UserUtils.clearCache(user);
        }
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:59
     * @Desc: 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
     */
    public List<User> findUserByOfficeId(String officeId) {
        List<User> list = (List<User>)CacheUtils.get(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId);
        if (list == null){
            User user = new User();
            user.setOffice(new Office(officeId));
            list = userDao.findUserByOfficeId(user);
            CacheUtils.put(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId, list);
        }
        return list;
    }


    /**
     * 获取Key加载信息
     */
    public static boolean printKeyLoadMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    欢迎使用 "+ Global.getConfig("productName")+"  - 麻雀虽小，五脏俱全\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return true;
    }
}
