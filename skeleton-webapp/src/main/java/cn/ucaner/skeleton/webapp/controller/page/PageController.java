package cn.ucaner.skeleton.webapp.controller.page;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.controller.page
 * @Description： <p> PageController - 初始页面路由  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 15:20
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Controller
public class PageController {

    private final static Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = "/", method = { RequestMethod.GET})
    public String indexFirst(){
        logger.info("redirect:/index.do");
        return "redirect:/index.do";
    }

    @PreAuthorize("authenticated and hasPermission('skip', 'index')")
    @RequestMapping(value = "/index.do", method = { RequestMethod.GET})
    public String index(){
        return "index";
    }



    @RequestMapping(value = "/welcome.do", method = { RequestMethod.GET})
    public String init(){
        return "welcome";
    }

}
