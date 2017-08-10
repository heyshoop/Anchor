package cn.sheetanchor.sparrow.sys.service;

import cn.sheetanchor.common.persistence.Page;
import cn.sheetanchor.sparrow.sys.model.Dict;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 阁楼麻雀
 * @Email: netuser.orz@icloud.com
 * @Date: 2017/8/10 上午11:00
 * @Des: 字典服务
 */
public interface DictService {
    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:39
     * @Desc: 通过ID查询字典
     */
    Dict get(String id);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:41
     * @Desc: 查询字段类型列表
     */
    List<String> findTypeList();

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:41
     * @Desc: 分页查询
     */
    Page<Dict> findPage(Page<Dict> OfficePage, Dict dict);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:42
     * @Desc: 保存
     */
    void save(Dict dict);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:43
     * @Desc: 删除
     */
    void delete(Dict dict);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:44
     * @Desc: 查询列表
     */
    List<Dict> findList(Dict dict);
}
