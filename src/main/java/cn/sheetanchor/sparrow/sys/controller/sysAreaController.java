package cn.sheetanchor.sparrow.sys.controller;

import cn.sheetanchor.common.utils.StringUtils;
import cn.sheetanchor.common.utils.UserUtils;
import cn.sheetanchor.common.web.BaseController;
import cn.sheetanchor.sparrow.sys.model.Area;
import cn.sheetanchor.sparrow.sys.service.AreaService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author: 阁楼麻雀
 * @Date: 2017/8/10 上午11:47
 * @Desc: 区域Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class sysAreaController extends BaseController {

	@Autowired
	private AreaService areaService;
	
	@ModelAttribute("area")
	public Area get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return areaService.get(id);
		}else{
			return new Area();
		}
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:48
	 * @Desc: 区域列表
	 */
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"list", ""})
	public String list(Area area, Model model) {
		model.addAttribute("list", areaService.findAll());
		return "modules/sys/areaList";
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:48
	 * @Desc: 区域表单详情
	 */
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "form")
	public String form(Area area, Model model) {
		if (area.getParent()==null||area.getParent().getId()==null){
			area.setParent(UserUtils.getUser().getOffice().getArea());
		}
		area.setParent(areaService.get(area.getParent().getId()));
		model.addAttribute("area", area);
		return "modules/sys/areaForm";
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:49
	 * @Desc: 区域保存
	 */
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "save")
	public String save(Area area, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, area)){
			return form(area, model);
		}
		areaService.save(area);
		addMessage(redirectAttributes, "保存区域'" + area.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/area/";
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:49
	 * @Desc: 区域删除
	 */
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "delete")
	public String delete(Area area, RedirectAttributes redirectAttributes) {
		areaService.delete(area);
		addMessage(redirectAttributes, "删除区域成功");
		return "redirect:" + adminPath + "/sys/area/";
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:49
	 * @Desc: 区域树形结构
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Area> list = areaService.findAll();
		for (int i=0; i<list.size(); i++){
			Area e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
