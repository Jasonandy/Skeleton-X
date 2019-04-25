/******************************************************************************
 * ~ Copyright (c) 2018 [jasonandy@hotmail.com | https://github.com/Jasonandy] *
 * ~                                                                           *
 * ~ Licensed under the Apache License, Version 2.0 (the "License”);           *
 * ~ you may not use this file except in compliance with the License.          *
 * ~ You may obtain a copy of the License at                                   *
 * ~                                                                           *
 * ~    http://www.apache.org/licenses/LICENSE-2.0                             *
 * ~                                                                           *
 * ~ Unless required by applicable law or agreed to in writing, software       *
 * ~ distributed under the License is distributed on an "AS IS" BASIS,         *
 * ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 * ~ See the License for the specific language governing permissions and       *
 * ~ limitations under the License.                                            *
 ******************************************************************************/
package cn.ucaner.skeleton.service.controller;

import cn.ucaner.skeleton.common.utils.pk.PKGenerator;
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
import java.util.Random;


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
        try {
            List<User> allUser =  userService.getAllUserList();
            respBody.addOK(allUser,"查询成功！");
            logger.info("查询所有用户-findAllUser:{}",allUser);
        } catch (Exception e) {
            respBody.addError(e.getMessage());
        }
        return respBody;
    }


    @ResponseBody
    @RequestMapping(value = "/insert",method= RequestMethod.GET)
    public RespBody randomInsert() {
        RespBody respBody = new RespBody();
        User user = new User();
        user.setId(PKGenerator.uuid32());
        user.setAge(20);
        user.setName("Skeleton.");
        user.setDesc("Skeleton..");
        userService.insert(user);
        logger.info("新增用户-randomInsert:{}",user);
        respBody.addOK(user,"插入成功!");
        return respBody;
    }


    @ResponseBody
    @RequestMapping(value = "/es",method= RequestMethod.GET)
    public RespBody esTest() {
        RespBody respBody = new RespBody();
        User user = new User();
        user.setId(PKGenerator.uuid32());
        user.setAge(new Random().nextInt(30));
        user.setName("Skeleton.ES..");
        user.setDesc("Skeleton..ES..");
        userService.saveByEs(user);
        logger.info("-ES新增用户-randomInsert:{}",user);
        respBody.addOK(user,"插入成功!");
        return respBody;
    }


    @ResponseBody
    @RequestMapping(value = "/esList",method= RequestMethod.GET)
    public RespBody esTestGet() {
        RespBody respBody = new RespBody();
        long startTime = System.currentTimeMillis();
        List allUserList = userService.getAllUserList();
        logger.info("当前load出来的数据Szie:{}",allUserList.size());
        long endTime = System.currentTimeMillis();
        respBody.addOK((endTime-startTime)+":ms ","总耗时时间.");
        return respBody;
    }


}
