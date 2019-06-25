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
package cn.ucaner.skeleton.codegen.mapper;

import cn.ucaner.skeleton.codegen.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.codegen.mapper
 * @Description： <p> UserMapper  </p>
 * @Author： - Jason
 * @CreatTime：2019/6/25 - 9:59
 * @Modify By：
 * @ModifyTime： 2019/6/25
 * @Modify marker：
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户列表
     * @param page
     * @param state
     * @return
     */
    @Select("selectUserList")
    List<User> selectUserList(Page page, String state);
}
