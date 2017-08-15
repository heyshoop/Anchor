package cn.sheetanchor.sparrow.demo.service;

import cn.sheetanchor.common.persistence.Page;
import cn.sheetanchor.sparrow.demo.model.Demo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 阁楼麻雀
 * @Email: netuser.orz@icloud.com
 * @Date: 2017/8/14 上午11:18
 * @Des:
 */
public interface DemoService {
    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/14 上午11:29
     * @Desc: 分页查询列表
     */
    Page<Demo> findPage(Page<Demo> demoPage, Demo demo);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/14 下午1:12
     * @Desc: 根据ID查询
     */
    Demo get(String id);

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/14 下午1:27
     * @Desc: 保存
     */
    void save(Demo demo);
}
