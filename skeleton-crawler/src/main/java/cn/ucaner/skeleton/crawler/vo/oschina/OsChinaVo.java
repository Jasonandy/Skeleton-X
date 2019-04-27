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
package cn.ucaner.skeleton.crawler.vo.oschina;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.vo.oschina
 * @Description： <p> OsChinaVo  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 17:56
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
@TargetUrl("http://www.oschina.net/question/\\d+_\\d+*")
@HelpUrl("http://www.oschina.net/question/*")
@ExtractBy(value = "//ul[@class='list']/li[@class='Answer']", multi = true)
public class OsChinaVo implements AfterExtractor {

    @ExtractBy("//img/@title")
    private String user;

    @ExtractBy("//div[@class='detail']")
    private String content;

    public static void main(String[] args) {
        OOSpider.create(Site.me(), OsChinaVo.class).addUrl("http://www.oschina.net/question/567527_120597").run();
    }

    @Override
    public void afterProcess(Page page) {
        System.out.println(page.getUrl());
    }
}
