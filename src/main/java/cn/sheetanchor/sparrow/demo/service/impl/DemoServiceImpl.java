package cn.sheetanchor.sparrow.demo.service.impl;

import cn.sheetanchor.common.service.CrudService;
import cn.sheetanchor.sparrow.demo.dao.DemoDao;
import cn.sheetanchor.sparrow.demo.model.Demo;
import cn.sheetanchor.sparrow.demo.service.DemoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 阁楼麻雀
 * @Email: netuser.orz@icloud.com
 * @Date: 2017/8/14 上午11:21
 * @Des:
 */
@Service
@Transactional(readOnly = true)
public class DemoServiceImpl extends CrudService<DemoDao, Demo> implements DemoService {


    @Transactional(readOnly = false)
    public void save(Demo demo) {
        super.save(demo);
    }
}
