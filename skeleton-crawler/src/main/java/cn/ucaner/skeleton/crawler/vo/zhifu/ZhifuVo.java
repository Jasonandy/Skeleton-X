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
package cn.ucaner.skeleton.crawler.vo.zhifu;

import cn.ucaner.skeleton.crawler.vo.BaseCrawlerVo;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.vo.zhifu
 * @Description： <p> ZhifuVo  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 14:35
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
public class ZhifuVo extends BaseCrawlerVo {

    /**
     * 网页url
     */
    private String url;

    /**
     * 描述
     */
    private String desc;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
