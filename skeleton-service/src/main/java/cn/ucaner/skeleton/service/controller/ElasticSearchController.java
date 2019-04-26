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

import cn.ucaner.skeleton.common.base.entity.BaseEntity;
import cn.ucaner.skeleton.common.utils.encrypt.MD5Utils;
import cn.ucaner.skeleton.common.utils.pk.PKGenerator;
import cn.ucaner.skeleton.common.vo.RespBody;
import cn.ucaner.skeleton.service.framework.common.base.dao.impl.ElasticSearchBaseDaoImpl;
import cn.ucaner.skeleton.service.user.entity.User;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;


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
    @ResponseBody
    @GetMapping(value="/add/{indexName}/{type}/{id}")
    public RespBody add2ElasticSearch(@PathVariable String indexName, @PathVariable String type, @RequestParam(required=false) String id){
        logger.info("---indexName:{},type:{},id:{}---",indexName,type,id);
        RespBody respBody = new RespBody();
        try {
            if (StringUtils.isBlank(id)){
                id = PKGenerator.uuid32();
            }

            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject()
                    .field("name", PKGenerator.randomBase62(8))
                    .field("age", new Random().nextInt(24))
                    .field("address", MD5Utils.getMD5(PKGenerator.uuid()))
                    .endObject();

            IndexResponse indexResponse = transportClient.
                    prepareIndex(indexName,type, id)
                    .setSource(xContentBuilder).execute().get();

            respBody.addOK(indexResponse," -- add2ElasticSearchSuccess  ---");
            logger.info("add2ElasticSearchSuccess:{}",indexResponse);
        } catch (Exception e) {
            respBody.addError(e.getMessage());
            logger.error(" --- add2ElasticSearch:{} ----",e.getMessage());
        }
        return respBody;
    }


    @ResponseBody
    @GetMapping(value="/addUser/{indexName}/{typeName}/{count}/{id}")
    public RespBody addUser(@PathVariable String indexName, @PathVariable String typeName,@PathVariable Integer count, @RequestParam(required=false) String id){
        logger.info("---indexName:{},type:{},count:{},id:{}---",indexName,typeName,count,id);
        RespBody respBody = new RespBody();
        try {
            if (StringUtils.isBlank(id)){
                id = PKGenerator.uuid32();
            }
            for (int i = 0; i < count; i++) {
                User user = new User();
                user.setId(id);
                user.setAge(new Random().nextInt(24));
                user.setName(PKGenerator.randomBase62(8));
                user.setDesc(MD5Utils.getMD5(PKGenerator.uuid()));

                String jsonStr = JSON.toJSONString(user);
                IndexResponse indexByJson = elasticSearchBaseDaoImpl.createIndexByJson(indexName, typeName, id, jsonStr);
                logger.info("addUser:{}",indexByJson);
            }
            respBody.addOK(id," -- addUser  ---");
        } catch (Exception e) {
            respBody.addError(e.getMessage());
            logger.error(" --- addUser:{} ----",e.getMessage());
        }
        return respBody;
    }

    @ResponseBody
    @GetMapping(value="/addBatchUser/{indexName}/{typeName}/{count}/{id}")
    public RespBody addBatchUser(@PathVariable String indexName, @PathVariable String typeName,@PathVariable Integer count, @RequestParam(required=false) String id){
        logger.info("---indexName:{},type:{},count:{},id:{}---",indexName,typeName,count,id);
        RespBody respBody = new RespBody();
        List<BaseEntity> lists  = new LinkedList<>();
        try {
            if (StringUtils.isBlank(id)){
                id = PKGenerator.uuid32();
            }
            for (int i = 0; i < count; i++) {
                User user = new User();
                user.setId(id);
                user.setAge(new Random().nextInt(24));
                user.setName(PKGenerator.randomBase62(8));
                user.setDesc(MD5Utils.getMD5(PKGenerator.uuid()));
                lists.add(user);
                logger.info("addBatchUser:{}",JSON.toJSONString(user));
            }
            logger.info("--- batch add Size:{} ---",lists.size());
            elasticSearchBaseDaoImpl.createIndexsByBeans(indexName,typeName,lists);
            lists.clear();
            respBody.addOK(id," -- addUser  ---");
        } catch (Exception e) {
            respBody.addError(e.getMessage());
            logger.error(" --- addBatchUser:{} ----",e.getMessage());
        }
        return respBody;
    }



    @ResponseBody
    @GetMapping(value="/cleanAll")
    public RespBody cleanAll(){
        RespBody respBody = new RespBody();
        try {
            elasticSearchBaseDaoImpl.cleanAllIndex();
            respBody.addOK("yes","--cleanAll success --- ");
        } catch (Exception e) {
            logger.error("---- cleanAll error:{} ---",e.getMessage());
            respBody.addError("---- cleanAll error ---");
        }
        return respBody;
    }


    @ApiOperation("== 获取indexName对应的所有的数据 === ")
    @ResponseBody
    @GetMapping(value="/getCount/{indexName}")
    public RespBody getCount(@PathVariable String indexName){
        RespBody respBody = new RespBody();
        try {
            long indexDataCount = elasticSearchBaseDaoImpl.getIndexDataCount(indexName);
            logger.info("==== indexName:{} getCount:{} ====",indexName,indexDataCount);
            respBody.addOK(indexDataCount,"--getCount success --- ");
        } catch (Exception e) {
            logger.error("---- getCount error:{} ---",e.getMessage());
            respBody.addError("---- getCount error ---");
        }
        return respBody;
    }


    @ResponseBody
    @GetMapping(value="/getAll/{indexName}")
    public RespBody getAll(@PathVariable String indexName){
        RespBody respBody = new RespBody();
        respBody.addOK(indexName,"getAll.");
        return respBody;
    }


    @ApiOperation("getHits")
    @ResponseBody
    @GetMapping(value="/getHits/{indexName}/{indexType}/{queryValue}")
    public RespBody getHits(@PathVariable String indexName,@PathVariable String indexType,@PathVariable String queryValue){
        RespBody respBody = new RespBody();
        try {
            List<String> hitsStringList = new ArrayList<>();
            String highligtFiled = "name";
            PageInfo<Object> pageInfo = new PageInfo<>();
            pageInfo.setPageNum(1);
            pageInfo.setPageSize(5);
            SearchHits searchHits = elasticSearchBaseDaoImpl.searchByOnlyValue(
                    indexName, indexType, highligtFiled, queryValue, highligtFiled, pageInfo);
            SearchHit[] hits = searchHits.getHits();
            for (SearchHit hit : hits) {
                hitsStringList.add(hit.getSourceAsString());
            }
            logger.info("---- hitsStringList:{} ---",hitsStringList);
            respBody.addOK(hitsStringList,"--getHits success --- ");
        } catch (Exception e) {
            logger.error("---- getHits error:{} ---",e.getMessage());
            respBody.addError("---- getHits error ---");
        }
        return respBody;
    }


    @ApiOperation("getWildcardQuery")
    @ResponseBody
    @GetMapping(value="/wild/{indexName}/{indexType}/{queryMap}")
    public RespBody getWildcardQuery(@PathVariable String indexName,@PathVariable String indexType,
                                     @RequestParam Map<String, String> queryMap){
        RespBody respBody = new RespBody();
        try {
            List lists = elasticSearchBaseDaoImpl.getDataByMuchIllegible(indexName, indexType, queryMap);
            logger.info("---- getWildcardQueryList:{} ---",lists);
            respBody.addOK(lists,"-- getWildcardQuery success --- ");
        } catch (Exception e) {
            logger.error("---- getWildcardQuery error:{} ---",e.getMessage());
            respBody.addError("---- getWildcardQuery error ---");
        }
        return respBody;
    }


    @ApiOperation("getIndex")
    @ResponseBody
    @GetMapping(value="/getIndex/{indexName}/{typeName}")
    public RespBody getIndexDataString(@PathVariable String indexName,@RequestParam(required=false)String typeName){
        RespBody respBody = new RespBody();
        try {
            String indexDataJsonStr;
            if (StringUtils.isEmpty(typeName)){
                 indexDataJsonStr = elasticSearchBaseDaoImpl.getIndexDataJsonStr(indexName);
            }else{
                indexDataJsonStr = elasticSearchBaseDaoImpl.getIndexDataJsonStr(indexName, typeName);
            }
            logger.info("---- getIndexDataString:{} ---",indexDataJsonStr);
            respBody.addOK(indexDataJsonStr,"-- getIndexDataString success --- ");
        } catch (Exception e) {
            logger.error("---- getIndexDataString error:{} ---",e.getMessage());
            respBody.addError("---- getIndexDataString error ---");
        }
        return respBody;
    }



}
