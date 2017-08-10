package cn.sheetanchor.sparrow.sys.controller;

import cn.sheetanchor.common.persistence.Page;
import cn.sheetanchor.common.utils.StringUtils;
import cn.sheetanchor.common.web.BaseController;
import cn.sheetanchor.sparrow.sys.model.Dict;
import cn.sheetanchor.sparrow.sys.service.DictService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author: 阁楼麻雀
 * @Date: 2017/8/10 上午10:58
 * @Desc: 字典controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class sysDictController extends BaseController {

	@Autowired
	private DictService dictService;

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:37
	 * @Desc: 字典查询
	 */
	@ModelAttribute
	public Dict get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return dictService.get(id);
		}else{
			return new Dict();
		}
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:39
	 * @Desc: 字典列表
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = {"list", ""})
	public String list(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> typeList = dictService.findTypeList();
		model.addAttribute("typeList", typeList);
        Page<Dict> page = dictService.findPage(new Page<Dict>(request, response), dict);
        model.addAttribute("page", page);
		return "modules/sys/dictList";
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:39
	 * @Desc: 字典表单
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "form")
	public String form(Dict dict, Model model) {
		model.addAttribute("dict", dict);
		return "modules/sys/dictForm";
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:38
	 * @Desc: 保存字典
	 */
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(Dict dict, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dict)){
			return form(dict, model);
		}
		dictService.save(dict);
		addMessage(redirectAttributes, "保存字典'" + dict.getLabel() + "'成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type="+dict.getType();
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:38
	 * @Desc: 删除字典
	 */
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "delete")
	public String delete(Dict dict, RedirectAttributes redirectAttributes) {
		dictService.delete(dict);
		addMessage(redirectAttributes, "删除字典成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type="+dict.getType();
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:38
	 * @Desc: 树结构查询
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findList(dict);
		for (int i=0; i<list.size(); i++){
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * @Author: 阁楼麻雀
	 * @Date: 2017/8/10 上午11:38
	 * @Desc: 列表数据查询
	 */
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<Dict> listData(@RequestParam(required=false) String type) {
		Dict dict = new Dict();
		dict.setType(type);
		return dictService.findList(dict);
	}

}
