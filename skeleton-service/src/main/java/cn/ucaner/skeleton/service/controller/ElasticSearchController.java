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
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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

//    @Autowired
//    private TransportClient transportClient;
//
//    @ResponseBody
//    @RequestMapping(value = "/add",method= RequestMethod.GET)
//    public RespBody testElasticSearch() {
//        RespBody respBody = new RespBody();
//        try {
//            String id = PKGenerator.uuid32();
//            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject()
//                    .field("name", "Jason")
//                    .field("age", 24)
//                    .field("address", "长沙市")
//                    .endObject();
//            IndexResponse indexResponse = transportClient.prepareIndex("MY_TEST",
//                "MY_TYPE", id).setSource(xContentBuilder).execute().get();
//            respBody.addOK(indexResponse,"插入到es成功!");
//            logger.info("插入到es成功!数据为:{}",indexResponse);
//        } catch (Exception e) {
//            respBody.addError(e.getMessage());
//            logger.error("插入异常！{}",e);
//        }
//        return respBody;
//    }


}
