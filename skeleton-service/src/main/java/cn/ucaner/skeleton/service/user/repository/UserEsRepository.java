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
package cn.ucaner.skeleton.service.user.repository;

import cn.ucaner.skeleton.service.user.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.user.repository
 * @Description： <p> UserEsRepository  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/25 - 11:01
 * @Modify By：
 * @ModifyTime： 2019/4/25
 * @Modify marker：
 */
@Service(value = "userEsRepository")
public interface  UserEsRepository extends ElasticsearchRepository<User, String> {

}
