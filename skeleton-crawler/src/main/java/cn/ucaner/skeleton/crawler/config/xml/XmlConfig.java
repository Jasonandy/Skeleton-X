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
package cn.ucaner.skeleton.crawler.config.xml;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.config.xml
 * @Description： <p> XmlConfig </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 13:58
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
@Configuration
@ImportResource(locations = {"classpath*:config/redisson/redisson.xml"})
public class XmlConfig {

}
