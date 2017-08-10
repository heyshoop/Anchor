package cn.sheetanchor.sparrow.sys.service.impl;

import cn.sheetanchor.common.service.CrudService;
import cn.sheetanchor.common.utils.CacheUtils;
import cn.sheetanchor.common.utils.DictUtils;
import cn.sheetanchor.sparrow.sys.dao.DictDao;
import cn.sheetanchor.sparrow.sys.model.Dict;
import cn.sheetanchor.sparrow.sys.service.DictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 阁楼麻雀
 * @Email: netuser.orz@icloud.com
 * @Date: 2017/8/10 上午11:24
 * @Des:
 */
@Service
@Transactional(readOnly = true)
public class DictServiceImpl extends CrudService<DictDao, Dict> implements DictService{

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:41
     * @Desc: 查询字段类型列表
     */
    public List<String> findTypeList() {
        return dao.findTypeList(new Dict());
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:42
     * @Desc: 保存并清理缓存
     */
    @Transactional(readOnly = false)
    public void save(Dict dict) {
        super.save(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 上午11:43
     * @Desc: 删除并清理缓存
     */
    @Transactional(readOnly = false)
    public void delete(Dict dict) {
        super.delete(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }
}
