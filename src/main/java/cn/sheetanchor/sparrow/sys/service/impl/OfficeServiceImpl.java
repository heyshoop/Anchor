package cn.sheetanchor.sparrow.sys.service.impl;

import cn.sheetanchor.common.service.TreeService;
import cn.sheetanchor.common.utils.UserUtils;
import cn.sheetanchor.sparrow.sys.dao.OfficeDao;
import cn.sheetanchor.sparrow.sys.model.Office;
import cn.sheetanchor.sparrow.sys.service.OfficeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2017/3/7
 * @Desc 部门service实现类
 */
@Service
@Transactional(readOnly = true)
public class OfficeServiceImpl extends TreeService<OfficeDao, Office> implements OfficeService{

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:52
     * @Desc: 查询list
     */
    public List<Office> findList(Boolean isAll) {
        if (isAll != null && isAll){
            return UserUtils.getOfficeAllList();
        }else{
            return UserUtils.getOfficeList();
        }
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:36
     * @Desc: 组织机构查询
     */
    @Transactional(readOnly = true)
    public List<Office> findList(Office office){
        if(office != null){
            office.setParentIds(office.getParentIds()+"%");
            return dao.findByParentIdsLike(office);
        }
        return  new ArrayList<Office>();
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:51
     * @Desc: 获取所有部门
     */
    public List<Office> findAll(){
        return UserUtils.getOfficeList();
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:37
     * @Desc: 保存组织机构信息
     */
    @Transactional(readOnly = false)
    public void save(Office office) {
        super.save(office);
        UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午3:38
     * @Desc: 删除组织机构信息
     */
    @Transactional(readOnly = false)
    public void delete(Office office) {
        super.delete(office);
        UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
    }
}
