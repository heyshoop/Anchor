package cn.sheetanchor.sparrow.sys.service;

import cn.sheetanchor.sparrow.sys.model.Area;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 阁楼麻雀
 * @Email: netuser.orz@icloud.com
 * @Date: 2017/8/10 上午11:49
 * @Des: 区域service
 */
public interface AreaService {
    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:57
     * @Desc: 查询
     */
    Area get(String id);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:57
     * @Desc: 查询全部
     */
    List<Area> findAll();

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:57
     * @Desc: 保存
     */
    void save(Area area);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:59
     * @Desc: 删除
     */
    void delete(Area area);
}
