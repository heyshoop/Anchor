package cn.sheetanchor.sparrow.sys.service;

import cn.sheetanchor.common.persistence.Page;
import cn.sheetanchor.sparrow.sys.model.Log;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 阁楼麻雀
 * @Email: netuser.orz@icloud.com
 * @Date: 2017/8/10 下午1:35
 * @Des: 日志service接口
 */
public interface LogService {
    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/10 下午1:38
     * @Desc: 分页查询
     */
    Page<Log> findPage(Page<Log> sysLogPage, Log log);
}
