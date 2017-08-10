package cn.sheetanchor.sparrow.sys.controller;

import cn.sheetanchor.common.persistence.Page;
import cn.sheetanchor.common.web.BaseController;
import cn.sheetanchor.sparrow.sys.model.Log;
import cn.sheetanchor.sparrow.sys.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 阁楼麻雀
 * @Date: 2017/8/10 下午1:32
 * @Desc: 日志Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class sysLogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	public String list(Log log, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Log> page = logService.findPage(new Page<Log>(request, response), log);
        model.addAttribute("page", page);
		return "modules/sys/logList";
	}

}
