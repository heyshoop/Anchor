package cn.sheetanchor.sparrow.sys.service;

import cn.sheetanchor.sparrow.sys.model.Office;

import java.util.List;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2017/3/7
 * @Desc 组织机构
 */
public interface OfficeService {
    /**
     * @Author 阁楼麻雀
     * @Date 2017/3/7 13:37
     * @Desc 组织机构-查询列表
     */
    List<Office> findList(Boolean isAll);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:51
     * @Desc: 获取所有部门
     */
    List<Office> findAll();

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:29
     * @Desc: 根据ID查询部门信息
     */
    Office get(String id);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:35
     * @Desc: 组织机构查询
     */
    List<Office> findList(Office office);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:36
     * @Desc: 保存组织机构信息
     */
    void save(Office office);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:37
     * @Desc: 删除组织机构信息
     */
    void delete(Office office);
}
