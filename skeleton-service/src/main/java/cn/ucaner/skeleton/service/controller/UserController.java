package cn.ucaner.skeleton.service.controller;

import cn.ucaner.skeleton.common.vo.RespBody;
import cn.ucaner.skeleton.service.user.entity.User;
import cn.ucaner.skeleton.service.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.controller
 * @Description： <p> UserController  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/19 - 12:01
 * @Modify By：
 * @ModifyTime： 2019/3/19
 * @Modify marker：
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/test",method= RequestMethod.GET)
    public RespBody findAllUser() {
        RespBody respBody = new RespBody();
        List<User> allUser =  userService.getAllUserList();
        respBody.addOK(allUser,"查询成功！");
        logger.info("查询成功！");
        return respBody;
    }
}
