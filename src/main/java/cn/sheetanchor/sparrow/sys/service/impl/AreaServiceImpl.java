package cn.sheetanchor.sparrow.sys.service.impl;

import cn.sheetanchor.common.service.TreeService;
import cn.sheetanchor.common.utils.UserUtils;
import cn.sheetanchor.sparrow.sys.dao.AreaDao;
import cn.sheetanchor.sparrow.sys.model.Area;
import cn.sheetanchor.sparrow.sys.service.AreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 阁楼麻雀
 * @Email: netuser.orz@icloud.com
 * @Date: 2017/8/10 上午11:50
 * @Des: 区域service实现类
 */
@Service
@Transactional(readOnly = true)
public class AreaServiceImpl extends TreeService<AreaDao, Area> implements AreaService{
    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:58
     * @Desc: 查询全部
     */
    public List<Area> findAll(){
        return UserUtils.getAreaList();
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:58
     * @Desc: 保存
     */
    @Transactional(readOnly = false)
    public void save(Area area) {
        super.save(area);
        UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:58
     * @Desc: 删除
     */
    @Transactional(readOnly = false)
    public void delete(Area area) {
        super.delete(area);
        UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }

}
