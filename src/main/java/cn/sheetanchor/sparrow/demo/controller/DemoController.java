package cn.sheetanchor.sparrow.demo.controller;

import cn.sheetanchor.common.persistence.Page;
import cn.sheetanchor.common.utils.StringUtils;
import cn.sheetanchor.common.web.BaseController;
import cn.sheetanchor.sparrow.demo.model.Demo;
import cn.sheetanchor.sparrow.demo.service.DemoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 阁楼麻雀
 * @Email: netuser.orz@icloud.com
 * @Date: 2017/8/14 上午11:16
 * @Des:
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/test")
public class DemoController extends BaseController{
    @Autowired
    private DemoService demoService;

    @ModelAttribute
    public Demo get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return demoService.get(id);
        }else{
            return new Demo();
        }
    }


    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/14 下午1:25
     * @Desc: 分页查询
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"list", ""})
    public String list(Demo demo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Demo> page = demoService.findPage(new Page<Demo>(request, response), demo);
        model.addAttribute("page", page);
        return "modules/demo/demoList";
    }

    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/14 下午1:25
     * @Desc: 根据ID查询
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "form")
    public String form(Demo demo, Model model) {
        model.addAttribute("demo", demo);
        return "modules/demo/demoForm";
    }


    /**
     * @Author: 阁楼麻雀
     * @Date: 2017/8/14 下午1:26
     * @Desc: 保存
     */
    @RequiresPermissions("sys:area:edit")
    @RequestMapping(value = "save")
    public String save(Demo demo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, demo)){
            return form(demo, model);
        }
        demoService.save(demo);
        addMessage(redirectAttributes, "保存'" + demo.getName() + "'成功");
        return "redirect:" + adminPath + "/demo/test/";
    }
}
