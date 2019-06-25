/*******************************************************************************
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
package cn.ucaner.skeleton.codegen.controller;

import cn.ucaner.skeleton.codegen.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.codegen.controller
 * @Description： <p> UserController </p>
 * @Author： - Jason
 * @CreatTime：2019/6/25 - 10:19
 * @Modify By：
 * @ModifyTime： 2019/6/25
 * @Modify marker：
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/page")
    public Object selectPage(Model model){
        Page page=new Page(1,20);
        page = userService.selectUserPage(page, "NORMAL");
        return page;
    }

}
