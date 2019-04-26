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
import cn.ucaner.skeleton.service.framework.common.base.dao.impl.ElasticSearchBaseDaoImpl;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.controller
 * @Description： <p> ElasticSearchController  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/26 - 13:20
 * @Modify By：
 * @ModifyTime： 2019/3/26
 * @Modify marker：
 */
@Controller
@RequestMapping("/es")
public class ElasticSearchController {

    private static Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);

    @Autowired
    private TransportClient transportClient;

    @Resource
    ElasticSearchBaseDaoImpl elasticSearchBaseDaoImpl;

    /**
     * add 添加数据到es里面去
     * @param indexName
     * @param type
     * @param id
     * @return
     */
    @GetMapping(value="/add/{indexName}/{type}/{id}")
    public RespBody testElasticSearch(@PathVariable String indexName, @PathVariable String type, @PathVariable(required=false) String id){
        logger.info("---indexName:{},type:{},id:{}---",indexName,type,id);
        RespBody respBody = new RespBody();
        try {
            if (StringUtils.isBlank(id)){
                id = PKGenerator.uuid32();
            }
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject()
                    .field("name", "Jason")
                    .field("age", 24)
                    .field("address", "长沙市")
                    .endObject();

            IndexResponse indexResponse = transportClient.
                    prepareIndex(indexName,type, id)
                    .setSource(xContentBuilder).execute().get();


            respBody.addOK(indexResponse,"插入到es成功!");
            logger.info("插入到es成功!数据为:{}",indexResponse);
        } catch (Exception e) {
            respBody.addError(e.getMessage());
            logger.error("插入异常！{}",e);
        }
        return respBody;
    }


    @GetMapping(value="/cleanAll")
    public RespBody cleanAll(){
        RespBody respBody = new RespBody();
        try {
            elasticSearchBaseDaoImpl.cleanAllIndex();
            respBody.addOK("--cleanAll success --- ");
        } catch (Exception e) {
            logger.error("---- cleanAll error:{} ---");
            respBody.addError("---- cleanAll error ---");
        }
        return respBody;
    }




}
