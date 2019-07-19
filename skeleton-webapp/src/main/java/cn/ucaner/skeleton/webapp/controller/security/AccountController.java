package cn.ucaner.skeleton.webapp.controller.security;

import cn.ucaner.skeleton.webapp.controller.chat.ChatController;
import cn.ucaner.skeleton.webapp.security.service.SysRoleService;
import cn.ucaner.skeleton.webapp.security.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.controller.security
 * @Description： <p> AccountController </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 15:22
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    private final static Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private SysRoleService sysRoleService;


    @ApiOperation(value = "登录页")
    @GetMapping("/login.do")
    public String loginAuth(Authentication authentication){
        logger.info("== account:login.do:{} ==",authentication);
        if(authentication == null){
            return "login";
        }
        return "index";
    }

}
