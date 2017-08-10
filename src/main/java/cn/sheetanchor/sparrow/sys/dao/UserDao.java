package cn.sheetanchor.sparrow.sys.dao;

import cn.sheetanchor.common.persistence.BaseDao;
import cn.sheetanchor.common.persistence.annotation.MyBatisDao;
import cn.sheetanchor.sparrow.sys.model.User;

import java.util.List;


/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2017/2/6
 * @Desc 用户Dao
 */
@MyBatisDao
public interface UserDao extends BaseDao<User>{

    /**
     * 根据登录名称查询用户
     * @param user
     * @return
     */
    public User getByLoginName(User user);

    /**
     * 更新登录信息，如：登录IP、登录时间
     * @param user
     * @return
     */
    public int updateLoginInfo(User user);

    /**
     * 更新用户密码
     * @param user
     * @return
     */
    public int updatePasswordById(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateUserInfo(User user);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:13
     * @Desc: 删除用户角色
     */
    void deleteUserRole(User user);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午2:13
     * @Desc: 增加用户角色
     */
    void insertUserRole(User user);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午4:00
     * @Desc: 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
     */
    List<User> findUserByOfficeId(User user);
}
