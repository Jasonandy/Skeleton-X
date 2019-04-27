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
 * @Description： <p> CnblogCrawler  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 14:52
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
@Crawler(name = "cnblogCrawler",queue = DefaultRedisQueue.class,useUnrepeated = false)
public class CnblogCrawler extends BaseSeimiCrawler {

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
                push(Request.build(s.toString(),"renderBean"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析数据
     * @param response
     */
    public void renderBean(Response response){
        try {
            String id = PKGenerator.uuid32();
            CnblogVo cnblogVo = response.render(CnblogVo.class);
            cnblogVo.setId(id);
            cnblogVo.setUrl(response.getUrl());
            elasticSearchBaseDao.createIndexByJson("blog","crawer",id,JSON.toJSONString(cnblogVo));
            logger.info("bean resolve res={},url={}",cnblogVo,response.getUrl());
            logger.info(JSON.toJSONString(cnblogVo));
        } catch (Exception e) {
            logger.error("--- render error:{} ----",e.getMessage());
        }
    }

}
