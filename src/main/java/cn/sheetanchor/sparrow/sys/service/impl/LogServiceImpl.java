package cn.sheetanchor.sparrow.sys.service.impl;

import cn.sheetanchor.common.persistence.Page;
import cn.sheetanchor.common.service.CrudService;
import cn.sheetanchor.common.utils.DateUtils;
import cn.sheetanchor.sparrow.sys.dao.LogDao;
import cn.sheetanchor.sparrow.sys.model.Log;
import cn.sheetanchor.sparrow.sys.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 阁楼麻雀
 * @Email: netuser.orz@icloud.com
 * @Date: 2017/8/10 下午1:35
 * @Des: 日志service实现类
 */
@Service
@Transactional(readOnly = true)
public class LogServiceImpl extends CrudService<LogDao, Log> implements LogService{

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:39
     * @Desc: 日志分页查询
     */
    public Page<Log> findPage(Page<Log> page, Log log) {

        // 设置默认时间范围，默认当前月
        if (log.getBeginDate() == null){
            log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
        }
        if (log.getEndDate() == null){
            log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
        }

        return super.findPage(page, log);

    }
}
