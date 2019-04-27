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
package cn.ucaner.skeleton.crawler.crawlers;

import cn.ucaner.skeleton.common.utils.pk.PKGenerator;
import cn.ucaner.skeleton.crawler.vo.cnblog.CnblogVo;
import cn.ucaner.skeleton.service.framework.common.base.dao.ElasticSearchBaseDao;
import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.def.DefaultRedisQueue;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.alibaba.fastjson.JSON;
import org.seimicrawler.xpath.JXDocument;

import javax.annotation.Resource;
import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.crawlers
 * @Description： <p> DefaultRedisQueueEG  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 13:34
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 * 使用seimicrawler提供的默认redis队列实现，独立启动需通过 {@link cn.wanghaomiao.seimi.config.SeimiConfig} 配置redis信息，
 * spring boot 请自行注册 {@link org.redisson.Redisson} bean
 * 相关配置请参考 https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 */
@Crawler(name = "redisCrawler",queue = DefaultRedisQueue.class,useUnrepeated = false)
public class DefaultRedisQueueEG extends BaseSeimiCrawler {

    @Resource
    ElasticSearchBaseDao elasticSearchBaseDao;

    @Override
    public String[] startUrls() {
        return new String[]{"http://www.cnblogs.com/"};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> urls = doc.sel("//a[@class='titlelnk']/@href");
            logger.info("{} ", urls.size());
            for (Object s:urls){
                push(Request.build(s.toString(),DefaultRedisQueueEG::getTitle));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getTitle(Response response){
        JXDocument doc = response.document();
        try {
            String id = PKGenerator.uuid32();
            CnblogVo cnblogVo = new CnblogVo();
            cnblogVo.setId(id);
            cnblogVo.setUrl(response.getUrl());
            cnblogVo.setDesc(doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()").toString());
            elasticSearchBaseDao.createIndexByJson("cnblog","hot",id,JSON.toJSONString(cnblogVo));
            logger.info("url :{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
